<<<<<<< HEAD
package com.vdc.tv.film.presentation.home

import com.vdc.tv.models.FindroidCollection
import com.vdc.tv.models.FindroidItem
=======
package dev.jdtech.jellyfin.film.presentation.home

import dev.jdtech.jellyfin.models.FindroidCollection
import dev.jdtech.jellyfin.models.FindroidItem
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface HomeAction {
    data class OnItemClick(val item: FindroidItem) : HomeAction

    data class OnLibraryClick(val library: FindroidCollection) : HomeAction

    data object OnRetryClick : HomeAction

    data object OnSearchClick : HomeAction

    data object OnSettingsClick : HomeAction

    data object OnManageServers : HomeAction
}
