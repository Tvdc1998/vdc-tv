<<<<<<< HEAD
package com.vdc.tv.core.presentation.downloader
=======
package dev.jdtech.jellyfin.core.presentation.downloader
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface DownloaderEvent {
    data object Successful : DownloaderEvent

    data object Deleted : DownloaderEvent
}
