<<<<<<< HEAD
package com.vdc.tv.film.presentation.person
=======
package dev.jdtech.jellyfin.film.presentation.person
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
<<<<<<< HEAD
import com.vdc.tv.models.FindroidMovie
import com.vdc.tv.models.FindroidShow
import com.vdc.tv.repository.JellyfinRepository
=======
import dev.jdtech.jellyfin.models.FindroidMovie
import dev.jdtech.jellyfin.models.FindroidShow
import dev.jdtech.jellyfin.repository.JellyfinRepository
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jellyfin.sdk.model.api.BaseItemKind

@HiltViewModel
class PersonViewModel @Inject internal constructor(private val repository: JellyfinRepository) :
    ViewModel() {
    private val _state = MutableStateFlow(PersonState())
    val state = _state.asStateFlow()

    fun loadPerson(personId: UUID) {
        viewModelScope.launch {
            try {
                val person = repository.getPerson(personId)

                val items =
                    repository.getPersonItems(
                        personIds = listOf(personId),
                        includeTypes = listOf(BaseItemKind.MOVIE, BaseItemKind.SERIES),
                        recursive = true,
                    )

                val movies = items.filterIsInstance<FindroidMovie>()
                val shows = items.filterIsInstance<FindroidShow>()

                _state.emit(
                    _state.value.copy(
                        person = person,
                        starredInMovies = movies,
                        starredInShows = shows,
                    )
                )
            } catch (e: Exception) {
                _state.emit(_state.value.copy(error = e))
            }
        }
    }
}
