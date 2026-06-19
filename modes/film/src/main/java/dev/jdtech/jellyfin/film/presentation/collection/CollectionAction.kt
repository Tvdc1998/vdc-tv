package com.vdc.tv.film.presentation.collection

import com.vdc.tv.models.FindroidItem

sealed interface CollectionAction {
    data class OnItemClick(val item: FindroidItem) : CollectionAction

    data object OnBackClick : CollectionAction
}
