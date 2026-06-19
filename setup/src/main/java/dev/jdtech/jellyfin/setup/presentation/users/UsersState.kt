package com.vdc.tv.setup.presentation.users

import com.vdc.tv.models.User

data class UsersState(
    val users: List<User> = emptyList(),
    val publicUsers: List<User> = emptyList(),
    val serverName: String? = null,
)
