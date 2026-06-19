<<<<<<< HEAD
package com.vdc.tv.setup.presentation.addserver
=======
package dev.jdtech.jellyfin.setup.presentation.addserver
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface AddServerAction {
    data class OnConnectClick(val address: String) : AddServerAction

    data object OnBackClick : AddServerAction
}
