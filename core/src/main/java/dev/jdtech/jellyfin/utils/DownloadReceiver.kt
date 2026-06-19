<<<<<<< HEAD
package com.vdc.tv.utils
=======
package dev.jdtech.jellyfin.utils
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
<<<<<<< HEAD
import com.vdc.tv.database.ServerDatabaseDao
import com.vdc.tv.models.FindroidItem
import com.vdc.tv.models.toFindroidEpisode
import com.vdc.tv.models.toFindroidMovie
import com.vdc.tv.models.toFindroidSource
import com.vdc.tv.repository.JellyfinRepository
=======
import dev.jdtech.jellyfin.database.ServerDatabaseDao
import dev.jdtech.jellyfin.models.FindroidItem
import dev.jdtech.jellyfin.models.toFindroidEpisode
import dev.jdtech.jellyfin.models.toFindroidMovie
import dev.jdtech.jellyfin.models.toFindroidSource
import dev.jdtech.jellyfin.repository.JellyfinRepository
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.io.File
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DownloadReceiver : BroadcastReceiver() {

    @Inject lateinit var database: ServerDatabaseDao

    @Inject lateinit var downloader: Downloader

    @Inject lateinit var repository: JellyfinRepository

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.DOWNLOAD_COMPLETE") {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (id != -1L) {
                val source = database.getSourceByDownloadId(id)
                if (source != null) {
                    val path = source.path.replace(".download", "")
                    val successfulRename = File(source.path).renameTo(File(path))
                    if (successfulRename) {
                        database.setSourcePath(source.id, path)
                    } else {
                        val items = mutableListOf<FindroidItem>()
                        items.addAll(
                            database.getMovies().map {
                                it.toFindroidMovie(database, repository.getUserId())
                            }
                        )
                        items.addAll(
                            database.getEpisodes().map {
                                it.toFindroidEpisode(database, repository.getUserId())
                            }
                        )

                        items
                            .firstOrNull { it.id == source.itemId }
                            ?.let {
                                CoroutineScope(Dispatchers.IO).launch {
                                    downloader.deleteItem(it, source.toFindroidSource(database))
                                }
                            }
                    }
                } else {
                    val mediaStream = database.getMediaStreamByDownloadId(id)
                    if (mediaStream != null) {
                        val path = mediaStream.path.replace(".download", "")
                        val successfulRename = File(mediaStream.path).renameTo(File(path))
                        if (successfulRename) {
                            database.setMediaStreamPath(mediaStream.id, path)
                        } else {
                            database.deleteMediaStream(mediaStream.id)
                        }
                    }
                }
            }
        }
    }
}
