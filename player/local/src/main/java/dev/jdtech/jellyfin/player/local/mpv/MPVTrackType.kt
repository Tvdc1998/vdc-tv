<<<<<<< HEAD
package com.vdc.tv.player.local.mpv
=======
package dev.jdtech.jellyfin.player.local.mpv
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.os.Parcelable
import androidx.media3.common.C
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MPVTrackType(val type: String) : Parcelable {
    VIDEO("video"),
    AUDIO("audio"),
    SUBTITLE("sub");

    companion object {
        fun fromMedia3TrackType(trackType: Int): MPVTrackType {
            return when (trackType) {
                C.TRACK_TYPE_VIDEO -> VIDEO
                C.TRACK_TYPE_AUDIO -> AUDIO
                C.TRACK_TYPE_TEXT -> SUBTITLE
                else -> SUBTITLE
            }
        }
    }
}
