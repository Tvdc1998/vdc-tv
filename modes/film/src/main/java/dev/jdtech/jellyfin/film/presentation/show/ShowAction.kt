<<<<<<< HEAD
package com.vdc.tv.film.presentation.show

import com.vdc.tv.models.FindroidItem
=======
package dev.jdtech.jellyfin.film.presentation.show

import dev.jdtech.jellyfin.models.FindroidItem
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID

sealed interface ShowAction {
    data class Play(val startFromBeginning: Boolean = false) : ShowAction

    data class PlayTrailer(val trailer: String) : ShowAction

    data object MarkAsPlayed : ShowAction

    data object UnmarkAsPlayed : ShowAction

    data object MarkAsFavorite : ShowAction

    data object UnmarkAsFavorite : ShowAction

    data object OnBackClick : ShowAction

    data object OnHomeClick : ShowAction

    data class NavigateToItem(val item: FindroidItem) : ShowAction

    data class NavigateToPerson(val personId: UUID) : ShowAction
}
