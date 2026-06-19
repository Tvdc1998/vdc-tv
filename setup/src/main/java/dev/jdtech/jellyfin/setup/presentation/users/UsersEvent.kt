package com.vdc.tv.setup.presentation.users

sealed interface UsersEvent {
    data object NavigateToHome : UsersEvent
}
