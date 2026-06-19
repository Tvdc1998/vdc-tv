<<<<<<< HEAD
package com.vdc.tv.film.presentation.movie

import com.vdc.tv.models.FindroidItemPerson
import com.vdc.tv.models.FindroidMovie
import com.vdc.tv.models.VideoMetadata
=======
package dev.jdtech.jellyfin.film.presentation.movie

import dev.jdtech.jellyfin.models.FindroidItemPerson
import dev.jdtech.jellyfin.models.FindroidMovie
import dev.jdtech.jellyfin.models.VideoMetadata
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class MovieState(
    val movie: FindroidMovie? = null,
    val videoMetadata: VideoMetadata? = null,
    val actors: List<FindroidItemPerson> = emptyList(),
    val director: FindroidItemPerson? = null,
    val writers: List<FindroidItemPerson> = emptyList(),
    val displayExtraInfo: Boolean = false,
    val error: Exception? = null,
)
