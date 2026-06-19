<<<<<<< HEAD
package com.vdc.tv.film.presentation.show

import com.vdc.tv.models.FindroidEpisode
import com.vdc.tv.models.FindroidItemPerson
import com.vdc.tv.models.FindroidSeason
import com.vdc.tv.models.FindroidShow
=======
package dev.jdtech.jellyfin.film.presentation.show

import dev.jdtech.jellyfin.models.FindroidEpisode
import dev.jdtech.jellyfin.models.FindroidItemPerson
import dev.jdtech.jellyfin.models.FindroidSeason
import dev.jdtech.jellyfin.models.FindroidShow
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class ShowState(
    val show: FindroidShow? = null,
    val nextUp: FindroidEpisode? = null,
    val seasons: List<FindroidSeason> = emptyList(),
    val actors: List<FindroidItemPerson> = emptyList(),
    val director: FindroidItemPerson? = null,
    val writers: List<FindroidItemPerson> = emptyList(),
    val error: Exception? = null,
)
