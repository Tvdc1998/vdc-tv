package com.vdc.tv.film.presentation.movie

import com.vdc.tv.models.FindroidItemPerson
import com.vdc.tv.models.FindroidMovie
import com.vdc.tv.models.VideoMetadata

data class MovieState(
    val movie: FindroidMovie? = null,
    val videoMetadata: VideoMetadata? = null,
    val actors: List<FindroidItemPerson> = emptyList(),
    val director: FindroidItemPerson? = null,
    val writers: List<FindroidItemPerson> = emptyList(),
    val displayExtraInfo: Boolean = false,
    val error: Exception? = null,
)
