package com.vdc.tv.film.presentation.person

import com.vdc.tv.models.FindroidMovie
import com.vdc.tv.models.FindroidPerson
import com.vdc.tv.models.FindroidShow

data class PersonState(
    val person: FindroidPerson? = null,
    val starredInMovies: List<FindroidMovie> = emptyList(),
    val starredInShows: List<FindroidShow> = emptyList(),
    val error: Exception? = null,
)
