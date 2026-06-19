<<<<<<< HEAD
package com.vdc.tv.film.presentation.episode

import com.vdc.tv.models.FindroidEpisode
import com.vdc.tv.models.FindroidItemPerson
import com.vdc.tv.models.VideoMetadata
=======
package dev.jdtech.jellyfin.film.presentation.episode

import dev.jdtech.jellyfin.models.FindroidEpisode
import dev.jdtech.jellyfin.models.FindroidItemPerson
import dev.jdtech.jellyfin.models.VideoMetadata
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class EpisodeState(
    val episode: FindroidEpisode? = null,
    val videoMetadata: VideoMetadata? = null,
    val actors: List<FindroidItemPerson> = emptyList(),
    val displayExtraInfo: Boolean = false,
    val error: Exception? = null,
)
