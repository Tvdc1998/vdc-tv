<<<<<<< HEAD
package com.vdc.tv.core.presentation.downloader

import com.vdc.tv.models.FindroidItem
=======
package dev.jdtech.jellyfin.core.presentation.downloader

import dev.jdtech.jellyfin.models.FindroidItem
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface DownloaderAction {
    data class Download(val item: FindroidItem, val storageIndex: Int = 0) : DownloaderAction

    data class DeleteDownload(val item: FindroidItem) : DownloaderAction

    data class CancelDownload(val item: FindroidItem) : DownloaderAction
}
