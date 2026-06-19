package com.vdc.tv.film.presentation.episode

import com.vdc.tv.models.FindroidEpisode
import com.vdc.tv.models.FindroidItemPerson
import com.vdc.tv.models.VideoMetadata

data class EpisodeState(
    val episode: FindroidEpisode? = null,
    val videoMetadata: VideoMetadata? = null,
    val actors: List<FindroidItemPerson> = emptyList(),
    val displayExtraInfo: Boolean = false,
    val error: Exception? = null,
)
