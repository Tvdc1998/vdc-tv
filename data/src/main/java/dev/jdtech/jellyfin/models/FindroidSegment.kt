<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import org.jellyfin.sdk.model.api.MediaSegmentDto
import org.jellyfin.sdk.model.api.MediaSegmentType

enum class FindroidSegmentType {
    INTRO,
    OUTRO,
    RECAP,
    PREVIEW,
    COMMERCIAL,
    UNKNOWN,
}

private fun MediaSegmentType.toFindroidSegmentType(): FindroidSegmentType =
    when (this) {
        MediaSegmentType.UNKNOWN -> FindroidSegmentType.UNKNOWN
        MediaSegmentType.INTRO -> FindroidSegmentType.INTRO
        MediaSegmentType.OUTRO -> FindroidSegmentType.OUTRO
        MediaSegmentType.RECAP -> FindroidSegmentType.RECAP
        MediaSegmentType.PREVIEW -> FindroidSegmentType.PREVIEW
        MediaSegmentType.COMMERCIAL -> FindroidSegmentType.COMMERCIAL
    }

data class FindroidSegment(val type: FindroidSegmentType, val startTicks: Long, val endTicks: Long)

fun FindroidSegmentDto.toFindroidSegment(): FindroidSegment {
    return FindroidSegment(type = type, startTicks = startTicks, endTicks = endTicks)
}

fun MediaSegmentDto.toFindroidSegment(): FindroidSegment {
    return FindroidSegment(
        type = type.toFindroidSegmentType(),
        startTicks = startTicks / 10000,
        endTicks = endTicks / 10000,
    )
}
