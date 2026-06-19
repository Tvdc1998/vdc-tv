package com.vdc.tv.core.presentation.downloader

import android.app.DownloadManager
import com.vdc.tv.models.UiText

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
