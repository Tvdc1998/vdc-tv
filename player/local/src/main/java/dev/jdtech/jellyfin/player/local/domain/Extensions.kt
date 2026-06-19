<<<<<<< HEAD
package com.vdc.tv.player.local.domain
=======
package dev.jdtech.jellyfin.player.local.domain
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.os.Build
import androidx.media3.common.Tracks
import java.util.Locale

fun List<Tracks.Group>.getTrackNames(): Array<String> {
    return this.map { group ->
            val nameParts: MutableList<String?> = mutableListOf()
            val format = group.mediaTrackGroup.getFormat(0)
            nameParts.run {
                add(format.label)
                add(
                    format.language?.let {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BAKLAVA) {
                            Locale.of(it.split("-").last()).displayLanguage
                        } else {
                            @Suppress("DEPRECATION") Locale(it.split("-").last()).displayLanguage
                        }
                    }
                )
                add(format.codecs)
                filterNotNull().joinToString(separator = " - ")
            }
        }
        .toTypedArray()
}
