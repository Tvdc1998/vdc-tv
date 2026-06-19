<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(
    tableName = "episodes",
    foreignKeys =
        [
            ForeignKey(
                entity = FindroidSeasonDto::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("seasonId"),
                onDelete = ForeignKey.CASCADE,
            ),
            ForeignKey(
                entity = FindroidShowDto::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("seriesId"),
                onDelete = ForeignKey.CASCADE,
            ),
        ],
    indices = [Index("seasonId"), Index("seriesId")],
)
data class FindroidEpisodeDto(
    @PrimaryKey val id: UUID,
    val serverId: String?,
    val seasonId: UUID,
    val seriesId: UUID,
    val name: String,
    val seriesName: String,
    val overview: String,
    val indexNumber: Int,
    val indexNumberEnd: Int?,
    val parentIndexNumber: Int,
    val runtimeTicks: Long,
    val premiereDate: LocalDateTime?,
    val communityRating: Float?,
    val chapters: List<FindroidChapter>?,
)

fun FindroidEpisode.toFindroidEpisodeDto(serverId: String? = null): FindroidEpisodeDto {
    return FindroidEpisodeDto(
        id = id,
        serverId = serverId,
        seasonId = seasonId,
        seriesId = seriesId,
        name = name,
        seriesName = seriesName,
        overview = overview,
        indexNumber = indexNumber,
        indexNumberEnd = indexNumberEnd,
        parentIndexNumber = parentIndexNumber,
        runtimeTicks = runtimeTicks,
        premiereDate = premiereDate,
        communityRating = communityRating,
        chapters = chapters,
    )
}
