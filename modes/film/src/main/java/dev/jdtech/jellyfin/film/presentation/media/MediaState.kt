<<<<<<< HEAD
package com.vdc.tv.film.presentation.media

import com.vdc.tv.models.FindroidCollection
=======
package dev.jdtech.jellyfin.film.presentation.media

import dev.jdtech.jellyfin.models.FindroidCollection
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class MediaState(
    val libraries: List<FindroidCollection> = emptyList(),
    val isLoading: Boolean = false,
    val error: Exception? = null,
)
