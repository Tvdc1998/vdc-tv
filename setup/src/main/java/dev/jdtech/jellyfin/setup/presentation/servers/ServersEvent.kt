package com.vdc.tv.setup.presentation.servers

sealed interface ServersEvent {
    data object ServerChanged : ServersEvent

    data object AddressChanged : ServersEvent
}
