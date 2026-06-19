<<<<<<< HEAD
package com.vdc.tv.film.presentation.person

import com.vdc.tv.models.FindroidItem
=======
package dev.jdtech.jellyfin.film.presentation.person

import dev.jdtech.jellyfin.models.FindroidItem
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface PersonAction {
    data object NavigateBack : PersonAction

    data object NavigateHome : PersonAction

    data class NavigateToItem(val item: FindroidItem) : PersonAction
}
