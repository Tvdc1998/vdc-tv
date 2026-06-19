package com.vdc.tv.setup.presentation.login

import com.vdc.tv.models.UiText

data class LoginState(
    val serverName: String? = null,
    val disclaimer: String? = null,
    val quickConnectEnabled: Boolean = false,
    val quickConnectCode: String? = null,
    val isLoading: Boolean = false,
    val error: UiText? = null,
)
