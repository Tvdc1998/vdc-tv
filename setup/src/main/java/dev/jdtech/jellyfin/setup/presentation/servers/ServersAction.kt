<<<<<<< HEAD
package com.vdc.tv.setup.presentation.servers
=======
package dev.jdtech.jellyfin.setup.presentation.servers
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import java.util.UUID

sealed interface ServersAction {
    data class OnServerClick(val serverId: String) : ServersAction

    data class OnAddressClick(val addressId: UUID) : ServersAction

    data class NavigateToAddresses(val serverId: String) : ServersAction

    data class DeleteServer(val serverId: String) : ServersAction

    data object OnAddClick : ServersAction

    data object OnBackClick : ServersAction
}
