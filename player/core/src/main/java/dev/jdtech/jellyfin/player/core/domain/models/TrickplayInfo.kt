<<<<<<< HEAD
package com.vdc.tv.player.core.domain.models
=======
package dev.jdtech.jellyfin.player.core.domain.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrickplayInfo(
    val width: Int,
    val height: Int,
    val tileWidth: Int,
    val tileHeight: Int,
    val thumbnailCount: Int,
    val interval: Int,
    val bandwidth: Int,
) : Parcelable
