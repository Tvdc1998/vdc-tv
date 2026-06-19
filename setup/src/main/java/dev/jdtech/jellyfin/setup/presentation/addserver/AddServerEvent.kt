package com.vdc.tv.setup.presentation.addserver

sealed interface AddServerEvent {
    data object Success : AddServerEvent
}
