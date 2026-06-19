package com.vdc.tv.core.presentation.downloader

sealed interface DownloaderEvent {
    data object Successful : DownloaderEvent

    data object Deleted : DownloaderEvent
}
