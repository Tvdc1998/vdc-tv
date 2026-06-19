<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.room.Entity
import java.util.UUID

@Entity(tableName = "segments", primaryKeys = ["itemId", "type"])
data class FindroidSegmentDto(
    val itemId: UUID,
    val type: FindroidSegmentType,
    val startTicks: Long,
    val endTicks: Long,
)

fun FindroidSegment.toFindroidSegmentsDto(itemId: UUID): FindroidSegmentDto {
    return FindroidSegmentDto(
        itemId = itemId,
        type = type,
        startTicks = startTicks,
        endTicks = endTicks,
    )
}
