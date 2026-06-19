<<<<<<< HEAD
package com.vdc.tv.film.presentation.collection

import com.vdc.tv.models.FindroidItem
=======
package dev.jdtech.jellyfin.film.presentation.collection

import dev.jdtech.jellyfin.models.FindroidItem
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface CollectionAction {
    data class OnItemClick(val item: FindroidItem) : CollectionAction

    data object OnBackClick : CollectionAction
}
