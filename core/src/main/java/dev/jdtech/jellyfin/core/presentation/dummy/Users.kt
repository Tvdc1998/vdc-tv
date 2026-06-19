package com.vdc.tv.core.presentation.dummy

import com.vdc.tv.models.User
import java.util.UUID

val dummyUser = User(id = UUID.randomUUID(), name = "Username", serverId = "")

val dummyUsers = listOf(dummyUser)
