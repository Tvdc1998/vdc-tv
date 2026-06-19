<<<<<<< HEAD
package com.vdc.tv.film.presentation.favorites
=======
package dev.jdtech.jellyfin.film.presentation.favorites
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
<<<<<<< HEAD
import com.vdc.tv.core.Constants
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.film.presentation.collection.CollectionState
import com.vdc.tv.models.CollectionSection
import com.vdc.tv.models.FindroidEpisode
import com.vdc.tv.models.FindroidMovie
import com.vdc.tv.models.FindroidShow
import com.vdc.tv.models.UiText
import com.vdc.tv.repository.JellyfinRepository
=======
import dev.jdtech.jellyfin.core.Constants
import dev.jdtech.jellyfin.core.R as CoreR
import dev.jdtech.jellyfin.film.presentation.collection.CollectionState
import dev.jdtech.jellyfin.models.CollectionSection
import dev.jdtech.jellyfin.models.FindroidEpisode
import dev.jdtech.jellyfin.models.FindroidMovie
import dev.jdtech.jellyfin.models.FindroidShow
import dev.jdtech.jellyfin.models.UiText
import dev.jdtech.jellyfin.repository.JellyfinRepository
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: JellyfinRepository) :
    ViewModel() {
    private val _state = MutableStateFlow(CollectionState())
    val state = _state.asStateFlow()

    fun loadItems() {
        viewModelScope.launch {
            _state.emit(_state.value.copy(isLoading = true, error = null))

            try {
                val items = repository.getFavoriteItems()

                val sections = mutableListOf<CollectionSection>()

                withContext(Dispatchers.Default) {
                    CollectionSection(
                            Constants.FAVORITE_TYPE_MOVIES,
                            UiText.StringResource(CoreR.string.movies_label),
                            items.filterIsInstance<FindroidMovie>(),
                        )
                        .let {
                            if (it.items.isNotEmpty()) {
                                sections.add(it)
                            }
                        }
                    CollectionSection(
                            Constants.FAVORITE_TYPE_SHOWS,
                            UiText.StringResource(CoreR.string.shows_label),
                            items.filterIsInstance<FindroidShow>(),
                        )
                        .let {
                            if (it.items.isNotEmpty()) {
                                sections.add(it)
                            }
                        }
                    CollectionSection(
                            Constants.FAVORITE_TYPE_EPISODES,
                            UiText.StringResource(CoreR.string.episodes_label),
                            items.filterIsInstance<FindroidEpisode>(),
                        )
                        .let {
                            if (it.items.isNotEmpty()) {
                                sections.add(it)
                            }
                        }
                }

                _state.emit(_state.value.copy(isLoading = false, sections = sections))
            } catch (e: Exception) {
                _state.emit(_state.value.copy(isLoading = false, error = e))
            }
        }
    }
}
