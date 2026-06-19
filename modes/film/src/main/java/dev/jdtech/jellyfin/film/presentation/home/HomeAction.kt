package com.vdc.tv.film.presentation.home

import com.vdc.tv.models.FindroidCollection
import com.vdc.tv.models.FindroidItem

sealed interface HomeAction {
    data class OnItemClick(val item: FindroidItem) : HomeAction

    data class OnLibraryClick(val library: FindroidCollection) : HomeAction

    data object OnRetryClick : HomeAction

    data object OnSearchClick : HomeAction

    data object OnSettingsClick : HomeAction

    data object OnManageServers : HomeAction
}
