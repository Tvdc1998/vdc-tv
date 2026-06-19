<<<<<<< HEAD
package com.vdc.tv.core.presentation.dummy

import com.vdc.tv.models.User
=======
package dev.jdtech.jellyfin.core.presentation.dummy

import dev.jdtech.jellyfin.models.User
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID

val dummyUser = User(id = UUID.randomUUID(), name = "Username", serverId = "")

val dummyUsers = listOf(dummyUser)
