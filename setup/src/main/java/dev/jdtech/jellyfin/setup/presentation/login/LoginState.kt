<<<<<<< HEAD
package com.vdc.tv.setup.presentation.login

import com.vdc.tv.models.UiText
=======
package dev.jdtech.jellyfin.setup.presentation.login

import dev.jdtech.jellyfin.models.UiText
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class LoginState(
    val serverName: String? = null,
    val disclaimer: String? = null,
    val quickConnectEnabled: Boolean = false,
    val quickConnectCode: String? = null,
    val isLoading: Boolean = false,
    val error: UiText? = null,
)
