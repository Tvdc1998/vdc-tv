<<<<<<< HEAD
package com.vdc.tv.setup.presentation.servers
=======
package dev.jdtech.jellyfin.setup.presentation.servers
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface ServersEvent {
    data object ServerChanged : ServersEvent

    data object AddressChanged : ServersEvent
}
