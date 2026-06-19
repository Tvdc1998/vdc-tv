<<<<<<< HEAD
package com.vdc.tv.film.presentation.movie
=======
package dev.jdtech.jellyfin.film.presentation.movie
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import java.util.UUID

sealed interface MovieAction {
    data class Play(val startFromBeginning: Boolean = false) : MovieAction

    data class PlayTrailer(val trailer: String) : MovieAction

    data object MarkAsPlayed : MovieAction

    data object UnmarkAsPlayed : MovieAction

    data object MarkAsFavorite : MovieAction

    data object UnmarkAsFavorite : MovieAction

    data object OnBackClick : MovieAction

    data object OnHomeClick : MovieAction

    data class NavigateToPerson(val personId: UUID) : MovieAction
}
