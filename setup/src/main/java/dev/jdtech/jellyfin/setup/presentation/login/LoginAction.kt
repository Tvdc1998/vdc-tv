<<<<<<< HEAD
package com.vdc.tv.setup.presentation.login
=======
package dev.jdtech.jellyfin.setup.presentation.login
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface LoginAction {
    data class OnLoginClick(val username: String, val password: String) : LoginAction

    data object OnChangeServerClick : LoginAction

    data object OnQuickConnectClick : LoginAction

    data object OnBackClick : LoginAction
}
