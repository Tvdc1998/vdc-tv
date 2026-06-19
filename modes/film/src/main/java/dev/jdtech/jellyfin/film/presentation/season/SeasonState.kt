<<<<<<< HEAD
package com.vdc.tv.film.presentation.season

import com.vdc.tv.models.FindroidEpisode
import com.vdc.tv.models.FindroidSeason
=======
package dev.jdtech.jellyfin.film.presentation.season

import dev.jdtech.jellyfin.models.FindroidEpisode
import dev.jdtech.jellyfin.models.FindroidSeason
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class SeasonState(
    val season: FindroidSeason? = null,
    val episodes: List<FindroidEpisode> = emptyList(),
    val error: Exception? = null,
)
