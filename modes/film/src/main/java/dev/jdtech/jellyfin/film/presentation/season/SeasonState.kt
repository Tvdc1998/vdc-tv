package com.vdc.tv.film.presentation.season

import com.vdc.tv.models.FindroidEpisode
import com.vdc.tv.models.FindroidSeason

data class SeasonState(
    val season: FindroidSeason? = null,
    val episodes: List<FindroidEpisode> = emptyList(),
    val error: Exception? = null,
)
