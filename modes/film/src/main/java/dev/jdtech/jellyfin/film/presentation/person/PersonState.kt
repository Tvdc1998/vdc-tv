<<<<<<< HEAD
package com.vdc.tv.film.presentation.person

import com.vdc.tv.models.FindroidMovie
import com.vdc.tv.models.FindroidPerson
import com.vdc.tv.models.FindroidShow
=======
package dev.jdtech.jellyfin.film.presentation.person

import dev.jdtech.jellyfin.models.FindroidMovie
import dev.jdtech.jellyfin.models.FindroidPerson
import dev.jdtech.jellyfin.models.FindroidShow
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class PersonState(
    val person: FindroidPerson? = null,
    val starredInMovies: List<FindroidMovie> = emptyList(),
    val starredInShows: List<FindroidShow> = emptyList(),
    val error: Exception? = null,
)
