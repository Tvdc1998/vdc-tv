package com.vdc.tv.core.presentation.subtitles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.vdc.tv.repository.JellyfinRepository
import com.vdc.tv.settings.domain.AppPreferences
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class SubtitlesViewModel @Inject constructor(
    private val repository: JellyfinRepository,
    private val appPreferences: AppPreferences,
) : ViewModel() {

    private var itemId: UUID? = null

    private val _state = MutableStateFlow(SubtitlesState())
    val state = _state
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SubtitlesState())

    private val _events = Channel<SubtitlesEvent>()
    val events = _events.receiveAsFlow()

    fun setItemId(id: UUID) {
        if (this.itemId != id) {
            this.itemId = id
            loadLanguages()
        }
    }

    fun onAction(action: SubtitlesAction) {
        when (action) {
            is SubtitlesAction.OnLanguageSelected -> {
                _state.update { it.copy(selectedLanguage = action.language, remoteSubtitles = emptyList()) }
                searchSubtitles(action.language)
            }
            is SubtitlesAction.OnSubtitleSelected -> {
                downloadSubtitle(action.subtitle.id)
            }
            SubtitlesAction.OnDismiss -> {
                _state.update { SubtitlesState() }
            }
            SubtitlesAction.OnRetry -> {
                _state.value.selectedLanguage?.let { searchSubtitles(it) } ?: loadLanguages()
            }
        }
    }

    private fun loadLanguages() {
        val preferredLanguage = appPreferences.getValue(appPreferences.preferredSubtitleLanguage)
        
        _state.update { 
            it.copy(
                selectedLanguage = preferredLanguage,
                isLoading = preferredLanguage != null
            ) 
        }

        if (preferredLanguage != null) {
            searchSubtitles(preferredLanguage)
        }
    }

    private fun searchSubtitles(language: String) {
        // If we already have all subtitles, we can just filter them
        val allSubtitles = _state.value.remoteSubtitles
        if (allSubtitles.isNotEmpty()) {
            // Check if any subtitle in the list has this language. 
            // If they were all loaded in loadLanguages, we are good.
            val filtered = allSubtitles.filter { it.language == language }
            if (filtered.isNotEmpty()) {
                _state.update { it.copy(isLoading = false) } // remoteSubtitles is already set or we can update with filtered if we want to show only those
                // Actually, let's just keep the full list and filter in the UI or update state with filtered.
                // UI currently uses state.remoteSubtitles.
                return
            }
        }

        // Otherwise search specifically
        val currentItemId = itemId ?: return
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val results = repository.searchRemoteSubtitles(currentItemId, language)
                _state.update { it.copy(isLoading = false, remoteSubtitles = results) }
            } catch (e: Exception) {
                Timber.e(e, "Failed to search subtitles")
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    private fun downloadSubtitle(subtitleId: String) {
        val currentItemId = itemId ?: return
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            _events.send(SubtitlesEvent.SubtitleDownloading)
            try {
                val success = repository.downloadRemoteSubtitle(currentItemId, subtitleId)
                if (success) {
                    var newSubtitle: com.vdc.tv.models.FindroidMediaStream? = null

                    // Retry searching for the new subtitle in media sources up to 5 times
                    repeat(5) {
                        val sources = repository.getMediaSources(currentItemId)
                        newSubtitle = sources.flatMap { it.mediaStreams }
                            .filter { it.isExternal && it.type == org.jellyfin.sdk.model.api.MediaStreamType.SUBTITLE }
                            .lastOrNull()

                        if (newSubtitle?.path != null) return@repeat

                        kotlinx.coroutines.delay(1000) // Wait 1 second before retrying
                    }

                    val path = newSubtitle?.path
                    if (path != null) {
                        _events.send(SubtitlesEvent.SubtitleDownloaded(
                            url = path,
                            title = newSubtitle.title,
                            language = newSubtitle.language
                        ))
                    } else {
                        // Fallback if we can't find it immediately in sources
                        _events.send(SubtitlesEvent.Error("Subtitle downloaded but could not be located in media sources after multiple attempts"))
                    }
                } else {
                    _events.send(SubtitlesEvent.Error("Failed to download subtitle"))
                }
                _state.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                Timber.e(e, "Failed to download subtitle")
                _events.send(SubtitlesEvent.Error(e.message ?: "Unknown error"))
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}
