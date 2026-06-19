<<<<<<< HEAD
package com.vdc.tv.utils

import com.vdc.tv.models.FindroidItem
import com.vdc.tv.models.FindroidSource
import com.vdc.tv.models.UiText
=======
package dev.jdtech.jellyfin.utils

import dev.jdtech.jellyfin.models.FindroidItem
import dev.jdtech.jellyfin.models.FindroidSource
import dev.jdtech.jellyfin.models.UiText
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

interface Downloader {
    suspend fun downloadItem(
        item: FindroidItem,
        sourceId: String,
        storageIndex: Int = 0,
    ): Pair<Long, UiText?>

    suspend fun cancelDownload(item: FindroidItem, downloadId: Long)

    suspend fun deleteItem(item: FindroidItem, source: FindroidSource)

    suspend fun getProgress(downloadId: Long?): Pair<Int, Int>
}
