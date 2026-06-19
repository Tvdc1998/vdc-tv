<<<<<<< HEAD
package com.vdc.tv.player.core.domain.models
=======
package dev.jdtech.jellyfin.player.core.domain.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.os.Parcelable
import java.util.UUID
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerItem(
    val name: String,
    val itemId: UUID,
    val mediaSourceId: String,
    val playbackPosition: Long,
    val mediaSourceUri: String = "",
    val parentIndexNumber: Int? = null,
    val indexNumber: Int? = null,
    val indexNumberEnd: Int? = null,
    val externalSubtitles: List<ExternalSubtitle> = emptyList(),
    val chapters: List<PlayerChapter> = emptyList(),
    val trickplayInfo: TrickplayInfo? = null,
) : Parcelable
