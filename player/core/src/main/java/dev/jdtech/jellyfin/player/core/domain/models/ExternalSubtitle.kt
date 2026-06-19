<<<<<<< HEAD
package com.vdc.tv.player.core.domain.models
=======
package dev.jdtech.jellyfin.player.core.domain.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ExternalSubtitle(
    val title: String,
    val language: String,
    val uri: Uri,
    val mimeType: String,
) : Parcelable
