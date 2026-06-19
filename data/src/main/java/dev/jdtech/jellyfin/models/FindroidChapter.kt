<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import kotlinx.serialization.Serializable
import org.jellyfin.sdk.model.api.BaseItemDto

@Serializable
data class FindroidChapter(
    /** The start position. */
    val startPosition: Long,
    /** The name. */
    val name: String? = null,
)

fun BaseItemDto.toFindroidChapters(): List<FindroidChapter> {
    return chapters?.map { chapter ->
        FindroidChapter(startPosition = chapter.startPositionTicks / 10000, name = chapter.name)
    } ?: emptyList()
}
