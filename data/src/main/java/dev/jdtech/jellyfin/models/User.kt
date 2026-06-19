<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "users",
    foreignKeys =
        [
            ForeignKey(
                entity = Server::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("serverId"),
                onDelete = ForeignKey.CASCADE,
            )
        ],
)
data class User(
    @PrimaryKey val id: UUID,
    val name: String,
    @ColumnInfo(index = true) val serverId: String,
    val accessToken: String? = null,
    val primaryImageTag: String? = null,
)
