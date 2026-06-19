<<<<<<< HEAD
package com.vdc.tv.film.presentation.season

import com.vdc.tv.models.FindroidItem
=======
package dev.jdtech.jellyfin.film.presentation.season

import dev.jdtech.jellyfin.models.FindroidItem
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID

sealed interface SeasonAction {
    data class Play(val startFromBeginning: Boolean = false) : SeasonAction

    data object MarkAsPlayed : SeasonAction

    data object UnmarkAsPlayed : SeasonAction

    data object MarkAsFavorite : SeasonAction

    data object UnmarkAsFavorite : SeasonAction

    data object OnBackClick : SeasonAction

    data object OnHomeClick : SeasonAction

    data class NavigateToItem(val item: FindroidItem) : SeasonAction

    data class NavigateToSeries(val seriesId: UUID) : SeasonAction
}
