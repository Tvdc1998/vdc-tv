package com.vdc.tv.film.presentation.media

import com.vdc.tv.models.FindroidCollection

sealed interface MediaAction {
    data class OnItemClick(val item: FindroidCollection) : MediaAction

    data object OnFavoritesClick : MediaAction

    data object OnRetryClick : MediaAction
}
