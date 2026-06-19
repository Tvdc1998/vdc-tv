<<<<<<< HEAD
package com.vdc.tv.core.presentation.downloader

import android.app.DownloadManager
import com.vdc.tv.models.UiText
=======
package dev.jdtech.jellyfin.core.presentation.downloader

import android.app.DownloadManager
import dev.jdtech.jellyfin.models.UiText
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class DownloaderState(
    val status: Int = 0,
    val progress: Float = 0f,
    val errorText: UiText? = null,
) {
    val isDownloading: Boolean
        get() =
            status in
                arrayOf(
                    DownloadManager.STATUS_PENDING,
                    DownloadManager.STATUS_RUNNING,
                    DownloadManager.STATUS_FAILED,
                )
}
