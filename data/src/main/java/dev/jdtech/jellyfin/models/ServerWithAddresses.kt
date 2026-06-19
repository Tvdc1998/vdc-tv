<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.room.Embedded
import androidx.room.Relation

data class ServerWithAddresses(
    @Embedded val server: Server,
    @Relation(parentColumn = "id", entityColumn = "serverId") val addresses: List<ServerAddress>,
    @Relation(parentColumn = "currentUserId", entityColumn = "id") val user: User?,
)
