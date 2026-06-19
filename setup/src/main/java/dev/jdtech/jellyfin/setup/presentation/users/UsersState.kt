<<<<<<< HEAD
package com.vdc.tv.setup.presentation.users

import com.vdc.tv.models.User
=======
package dev.jdtech.jellyfin.setup.presentation.users

import dev.jdtech.jellyfin.models.User
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class UsersState(
    val users: List<User> = emptyList(),
    val publicUsers: List<User> = emptyList(),
    val serverName: String? = null,
)
