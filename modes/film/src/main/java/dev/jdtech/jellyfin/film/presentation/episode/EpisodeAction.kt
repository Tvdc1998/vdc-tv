<<<<<<< HEAD
package com.vdc.tv.film.presentation.episode
=======
package dev.jdtech.jellyfin.film.presentation.episode
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import java.util.UUID

sealed interface EpisodeAction {
    data class Play(val startFromBeginning: Boolean = false) : EpisodeAction

    data object MarkAsPlayed : EpisodeAction

    data object UnmarkAsPlayed : EpisodeAction

    data object MarkAsFavorite : EpisodeAction

    data object UnmarkAsFavorite : EpisodeAction

    data object OnBackClick : EpisodeAction

    data object OnHomeClick : EpisodeAction

    data class NavigateToPerson(val personId: UUID) : EpisodeAction

    data class NavigateToSeason(val seasonId: UUID) : EpisodeAction
}
