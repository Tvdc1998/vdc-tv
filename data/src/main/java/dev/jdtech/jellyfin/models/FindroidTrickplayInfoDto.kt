<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "trickplayInfos",
    foreignKeys =
        [
            ForeignKey(
                entity = FindroidSourceDto::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("sourceId"),
                onDelete = ForeignKey.CASCADE,
            )
        ],
)
data class FindroidTrickplayInfoDto(
    @PrimaryKey val sourceId: String,
    val width: Int,
    val height: Int,
    val tileWidth: Int,
    val tileHeight: Int,
    val thumbnailCount: Int,
    val interval: Int,
    val bandwidth: Int,
)

fun FindroidTrickplayInfo.toFindroidTrickplayInfoDto(sourceId: String): FindroidTrickplayInfoDto {
    return FindroidTrickplayInfoDto(
        sourceId = sourceId,
        width = width,
        height = height,
        tileWidth = tileWidth,
        tileHeight = tileHeight,
        thumbnailCount = thumbnailCount,
        interval = interval,
        bandwidth = bandwidth,
    )
}
