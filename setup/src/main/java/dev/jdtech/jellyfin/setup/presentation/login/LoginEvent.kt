package com.vdc.tv.setup.presentation.login

sealed interface LoginEvent {
    data object Success : LoginEvent
}
