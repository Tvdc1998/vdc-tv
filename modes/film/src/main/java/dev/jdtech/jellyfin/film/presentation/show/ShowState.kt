package com.vdc.tv.film.presentation.show

import com.vdc.tv.models.FindroidEpisode
import com.vdc.tv.models.FindroidItemPerson
import com.vdc.tv.models.FindroidSeason
import com.vdc.tv.models.FindroidShow

data class ShowState(
    val show: FindroidShow? = null,
    val nextUp: FindroidEpisode? = null,
    val seasons: List<FindroidSeason> = emptyList(),
    val actors: List<FindroidItemPerson> = emptyList(),
    val director: FindroidItemPerson? = null,
    val writers: List<FindroidItemPerson> = emptyList(),
    val error: Exception? = null,
)
