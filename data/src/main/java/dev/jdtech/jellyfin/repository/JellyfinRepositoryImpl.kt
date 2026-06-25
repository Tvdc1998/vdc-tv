package com.vdc.tv.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vdc.tv.api.JellyfinApi
import com.vdc.tv.api.SubtitleApi
import com.vdc.tv.database.ServerDatabaseDao
import com.vdc.tv.models.FindroidCollection
import com.vdc.tv.models.FindroidEpisode
import com.vdc.tv.models.FindroidItem
import com.vdc.tv.models.FindroidMovie
import com.vdc.tv.models.FindroidPerson
import com.vdc.tv.models.FindroidSeason
import com.vdc.tv.models.FindroidSegment
import com.vdc.tv.models.FindroidShow
import com.vdc.tv.models.FindroidSource
import com.vdc.tv.models.RemoteSubtitleDto
import com.vdc.tv.models.SortBy
import com.vdc.tv.models.SortOrder
import com.vdc.tv.models.toFindroidCollection
import com.vdc.tv.models.toFindroidEpisode
import com.vdc.tv.models.toFindroidItem
import com.vdc.tv.models.toFindroidMovie
import com.vdc.tv.models.toFindroidPerson
import com.vdc.tv.models.toFindroidSeason
import com.vdc.tv.models.toFindroidSegment
import com.vdc.tv.models.toFindroidShow
import com.vdc.tv.models.toFindroidSource
import com.vdc.tv.settings.domain.AppPreferences
import java.io.File
import java.util.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.jellyfin.sdk.model.api.BaseItemDto
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.DeviceOptionsDto
import org.jellyfin.sdk.model.api.DeviceProfile
import org.jellyfin.sdk.model.api.GeneralCommandType
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.api.ItemFilter
import org.jellyfin.sdk.model.api.ItemSortBy
import org.jellyfin.sdk.model.api.MediaType
import org.jellyfin.sdk.model.api.PlayMethod
import org.jellyfin.sdk.model.api.PlaybackInfoDto
import org.jellyfin.sdk.model.api.PlaybackOrder
import org.jellyfin.sdk.model.api.PlaybackProgressInfo
import org.jellyfin.sdk.model.api.PlaybackStartInfo
import org.jellyfin.sdk.model.api.PlaybackStopInfo
import org.jellyfin.sdk.model.api.PublicSystemInfo
import org.jellyfin.sdk.model.api.RepeatMode
import org.jellyfin.sdk.model.api.SortOrder as ItemSortOrder
import org.jellyfin.sdk.model.api.SubtitleDeliveryMethod
import org.jellyfin.sdk.model.api.SubtitleProfile
import org.jellyfin.sdk.model.api.UserConfiguration
import timber.log.Timber

class JellyfinRepositoryImpl(
    private val context: Context,
    private val jellyfinApi: JellyfinApi,
    private val database: ServerDatabaseDao,
    private val appPreferences: AppPreferences,
    private val subtitleApi: SubtitleApi,
) : JellyfinRepository {
    override suspend fun getPublicSystemInfo(): PublicSystemInfo =
        withContext(Dispatchers.IO) { jellyfinApi.systemApi.getPublicSystemInfo().content }

    override suspend fun getUserViews(): List<BaseItemDto> =
        withContext(Dispatchers.IO) {
            val userId = jellyfinApi.userId ?: return@withContext emptyList()
            jellyfinApi.viewsApi.getUserViews(userId).content.items
        }

    override suspend fun getEpisode(itemId: UUID): FindroidEpisode =
        withContext(Dispatchers.IO) {
            jellyfinApi.userLibraryApi
                .getItem(itemId, jellyfinApi.userId!!)
                .content
                .toFindroidEpisode(this@JellyfinRepositoryImpl, database)!!
        }

    override suspend fun getMovie(itemId: UUID): FindroidMovie =
        withContext(Dispatchers.IO) {
            jellyfinApi.userLibraryApi
                .getItem(itemId, jellyfinApi.userId!!)
                .content
                .toFindroidMovie(this@JellyfinRepositoryImpl, database)
        }

    override suspend fun getShow(itemId: UUID): FindroidShow =
        withContext(Dispatchers.IO) {
            jellyfinApi.userLibraryApi
                .getItem(itemId, jellyfinApi.userId!!)
                .content
                .toFindroidShow(this@JellyfinRepositoryImpl)
        }

    override suspend fun getSeason(itemId: UUID): FindroidSeason =
        withContext(Dispatchers.IO) {
            jellyfinApi.userLibraryApi
                .getItem(itemId, jellyfinApi.userId!!)
                .content
                .toFindroidSeason(this@JellyfinRepositoryImpl)
        }

    override suspend fun getLibraries(): List<FindroidCollection> =
        withContext(Dispatchers.IO) {
            jellyfinApi.itemsApi.getItems(jellyfinApi.userId!!).content.items.mapNotNull {
                it.toFindroidCollection(this@JellyfinRepositoryImpl)
            }
        }

    override suspend fun getItem(itemId: UUID): FindroidItem? =
        withContext(Dispatchers.IO) {
            jellyfinApi.userLibraryApi
                .getItem(itemId = itemId, userId = jellyfinApi.userId!!)
                .content
                .toFindroidItem(this@JellyfinRepositoryImpl)
        }

    override suspend fun getItems(
        parentId: UUID?,
        includeTypes: List<BaseItemKind>?,
        recursive: Boolean,
        sortBy: SortBy,
        sortOrder: SortOrder,
        filters: List<ItemFilter>?,
        genreIds: List<UUID>?,
        startIndex: Int?,
        limit: Int?,
    ): List<FindroidItem> =
        withContext(Dispatchers.IO) {
            jellyfinApi.itemsApi
                .getItems(
                    jellyfinApi.userId!!,
                    parentId = parentId,
                    includeItemTypes = includeTypes,
                    recursive = recursive,
                    sortBy = listOf(ItemSortBy.fromName(sortBy.sortString)),
                    sortOrder = listOf(ItemSortOrder.fromName(sortOrder.sortString)),
                    filters = filters,
                    genreIds = genreIds,
                    startIndex = startIndex,
                    limit = limit,
                )
                .content
                .items
                .mapNotNull { it.toFindroidItem(this@JellyfinRepositoryImpl, database) }
        }

    override suspend fun getItemsPaging(
        parentId: UUID?,
        includeTypes: List<BaseItemKind>?,
        recursive: Boolean,
        sortBy: SortBy,
        sortOrder: SortOrder,
        filters: List<ItemFilter>?,
        genreIds: List<UUID>?,
    ): Flow<PagingData<FindroidItem>> {
        return Pager(
                config = PagingConfig(pageSize = 10, enablePlaceholders = false),
                pagingSourceFactory = {
                    ItemsPagingSource(this, parentId, includeTypes, recursive, sortBy, sortOrder, filters, genreIds)
                },
            )
            .flow
    }

    override suspend fun getPerson(personId: UUID): FindroidPerson =
        withContext(Dispatchers.IO) {
            jellyfinApi.userLibraryApi
                .getItem(personId, jellyfinApi.userId!!)
                .content
                .toFindroidPerson(this@JellyfinRepositoryImpl)
        }

    override suspend fun getPersonItems(
        personIds: List<UUID>,
        includeTypes: List<BaseItemKind>?,
        recursive: Boolean,
    ): List<FindroidItem> =
        withContext(Dispatchers.IO) {
            jellyfinApi.itemsApi
                .getItems(
                    jellyfinApi.userId!!,
                    personIds = personIds,
                    includeItemTypes = includeTypes,
                    recursive = recursive,
                )
                .content
                .items
                .mapNotNull { it.toFindroidItem(this@JellyfinRepositoryImpl, database) }
        }

    override suspend fun getFavoriteItems(): List<FindroidItem> =
        withContext(Dispatchers.IO) {
            jellyfinApi.itemsApi
                .getItems(
                    jellyfinApi.userId!!,
                    filters = listOf(ItemFilter.IS_FAVORITE),
                    includeItemTypes =
                        listOf(BaseItemKind.MOVIE, BaseItemKind.SERIES, BaseItemKind.EPISODE),
                    recursive = true,
                )
                .content
                .items
                .mapNotNull { it.toFindroidItem(this@JellyfinRepositoryImpl, database) }
        }

    override suspend fun getSearchItems(query: String): List<FindroidItem> =
        withContext(Dispatchers.IO) {
            jellyfinApi.itemsApi
                .getItems(
                    jellyfinApi.userId!!,
                    searchTerm = query,
                    includeItemTypes = listOf(BaseItemKind.MOVIE, BaseItemKind.SERIES),
                    recursive = true,
                )
                .content
                .items
                .mapNotNull { it.toFindroidItem(this@JellyfinRepositoryImpl, database) }
        }

    override suspend fun getSuggestions(): List<FindroidItem> =
        withContext(Dispatchers.IO) {
            val userId = jellyfinApi.userId ?: return@withContext emptyList()
            jellyfinApi.suggestionsApi
                .getSuggestions(
                    userId,
                    limit = 6,
                    type = listOf(BaseItemKind.MOVIE, BaseItemKind.SERIES),
                )
                .content
                .items
                .mapNotNull { it.toFindroidItem(this@JellyfinRepositoryImpl, database) }
        }

    override suspend fun getResumeItems(): List<FindroidItem> =
        withContext(Dispatchers.IO) {
            val userId = jellyfinApi.userId ?: return@withContext emptyList()
            jellyfinApi.itemsApi
                .getResumeItems(
                    userId,
                    limit = 12,
                    includeItemTypes = listOf(BaseItemKind.MOVIE, BaseItemKind.EPISODE),
                )
                .content
                .items
                .mapNotNull { it.toFindroidItem(this@JellyfinRepositoryImpl, database) }
        }

    override suspend fun getLatestMedia(parentId: UUID): List<FindroidItem> =
        withContext(Dispatchers.IO) {
            jellyfinApi.userLibraryApi
                .getLatestMedia(jellyfinApi.userId!!, parentId = parentId, limit = 16)
                .content
                .mapNotNull { it.toFindroidItem(this@JellyfinRepositoryImpl, database) }
        }

    override suspend fun getItemsByGenre(genreName: String, limit: Int): List<FindroidItem> =
        withContext(Dispatchers.IO) {
            jellyfinApi.itemsApi
                .getItems(
                    jellyfinApi.userId!!,
                    genres = listOf(genreName),
                    includeItemTypes = listOf(BaseItemKind.MOVIE, BaseItemKind.SERIES),
                    recursive = true,
                    sortBy = listOf(ItemSortBy.DATE_CREATED),
                    sortOrder = listOf(ItemSortOrder.DESCENDING),
                    limit = limit,
                )
                .content
                .items
                .mapNotNull { it.toFindroidItem(this@JellyfinRepositoryImpl, database) }
        }

    override suspend fun getGenres(parentId: UUID): List<FindroidItem> =
        withContext(Dispatchers.IO) {
            jellyfinApi.itemsApi
                .getItems(
                    userId = jellyfinApi.userId!!,
                    parentId = parentId,
                    includeItemTypes = listOf(BaseItemKind.GENRE),
                    recursive = true,
                )
                .content
                .items
                .mapNotNull { itemDto -> itemDto.toFindroidItem(this@JellyfinRepositoryImpl, database) }
        }

    override suspend fun getSeasons(seriesId: UUID, offline: Boolean): List<FindroidSeason> =
        withContext(Dispatchers.IO) {
            if (!offline) {
                jellyfinApi.showsApi.getSeasons(seriesId, jellyfinApi.userId!!).content.items.map {
                    it.toFindroidSeason(this@JellyfinRepositoryImpl)
                }
            } else {
                database.getSeasonsByShowId(seriesId).map {
                    it.toFindroidSeason(database, jellyfinApi.userId!!)
                }
            }
        }

    override suspend fun getNextUp(seriesId: UUID?): List<FindroidEpisode> =
        withContext(Dispatchers.IO) {
            jellyfinApi.showsApi
                .getNextUp(
                    jellyfinApi.userId!!,
                    limit = 24,
                    seriesId = seriesId,
                    enableResumable = false,
                )
                .content
                .items
                .mapNotNull { it.toFindroidEpisode(this@JellyfinRepositoryImpl) }
        }

    override suspend fun getEpisodes(
        seriesId: UUID,
        seasonId: UUID,
        fields: List<ItemFields>?,
        startItemId: UUID?,
        limit: Int?,
        offline: Boolean,
    ): List<FindroidEpisode> =
        withContext(Dispatchers.IO) {
            if (!offline) {
                jellyfinApi.showsApi
                    .getEpisodes(
                        seriesId,
                        jellyfinApi.userId!!,
                        seasonId = seasonId,
                        fields = fields,
                        startItemId = startItemId,
                        limit = limit,
                    )
                    .content
                    .items
                    .mapNotNull { it.toFindroidEpisode(this@JellyfinRepositoryImpl, database) }
            } else {
                database.getEpisodesBySeasonId(seasonId).map {
                    it.toFindroidEpisode(database, jellyfinApi.userId!!)
                }
            }
        }

    override suspend fun getMediaSources(itemId: UUID, includePath: Boolean): List<FindroidSource> =
        withContext(Dispatchers.IO) {
            val sources = mutableListOf<FindroidSource>()
            sources.addAll(
                jellyfinApi.mediaInfoApi
                    .getPostedPlaybackInfo(
                        itemId,
                        PlaybackInfoDto(
                            userId = jellyfinApi.userId!!,
                            deviceProfile =
                                DeviceProfile(
                                    name = "Direct play all",
                                    maxStaticBitrate = 1_000_000_000,
                                    maxStreamingBitrate = 1_000_000_000,
                                    codecProfiles = emptyList(),
                                    containerProfiles = emptyList(),
                                    directPlayProfiles = emptyList(),
                                    transcodingProfiles = emptyList(),
                                    subtitleProfiles =
                                        listOf(
                                            SubtitleProfile("srt", SubtitleDeliveryMethod.EXTERNAL),
                                            SubtitleProfile("ass", SubtitleDeliveryMethod.EXTERNAL),
                                        ),
                                ),
                            maxStreamingBitrate = 1_000_000_000,
                        ),
                    )
                    .content
                    .mediaSources
                    .map { it.toFindroidSource(this@JellyfinRepositoryImpl, itemId, includePath) }
            )
            sources.addAll(database.getSources(itemId).map { it.toFindroidSource(database) })
            sources
        }

    override suspend fun getStreamUrl(itemId: UUID, mediaSourceId: String): String =
        withContext(Dispatchers.IO) {
            try {
                jellyfinApi.videosApi.getVideoStreamUrl(
                    itemId,
                    static = true,
                    mediaSourceId = mediaSourceId,
                )
            } catch (e: Exception) {
                Timber.e(e)
                ""
            }
        }

    override suspend fun getSegments(itemId: UUID): List<FindroidSegment> =
        withContext(Dispatchers.IO) {
            val databaseSegments = database.getSegments(itemId).map { it.toFindroidSegment() }

            if (databaseSegments.isNotEmpty()) {
                return@withContext databaseSegments
            }

            try {
                val apiSegments =
                    jellyfinApi.mediaSegmentsApi.getItemSegments(itemId).content.items.map {
                        it.toFindroidSegment()
                    }

                return@withContext apiSegments
            } catch (e: Exception) {
                Timber.e(e)
                return@withContext emptyList()
            }
        }

    override suspend fun getTrickplayData(itemId: UUID, width: Int, index: Int): ByteArray? =
        withContext(Dispatchers.IO) {
            try {
                try {
                    val sources = File(context.filesDir, "trickplay/$itemId").listFiles()
                    if (sources != null) {
                        return@withContext File(sources.first(), index.toString()).readBytes()
                    }
                } catch (_: Exception) {}

                return@withContext jellyfinApi.trickplayApi
                    .getTrickplayTileImage(itemId, width, index)
                    .content
            } catch (_: Exception) {
                return@withContext null
            }
        }

    override suspend fun postCapabilities() {
        Timber.d("Sending capabilities")
        withContext(Dispatchers.IO) {
            jellyfinApi.sessionApi.postCapabilities(
                playableMediaTypes = listOf(MediaType.VIDEO),
                supportedCommands =
                    listOf(
                        GeneralCommandType.VOLUME_UP,
                        GeneralCommandType.VOLUME_DOWN,
                        GeneralCommandType.TOGGLE_MUTE,
                        GeneralCommandType.SET_AUDIO_STREAM_INDEX,
                        GeneralCommandType.SET_SUBTITLE_STREAM_INDEX,
                        GeneralCommandType.MUTE,
                        GeneralCommandType.UNMUTE,
                        GeneralCommandType.SET_VOLUME,
                        GeneralCommandType.DISPLAY_MESSAGE,
                        GeneralCommandType.PLAY,
                        GeneralCommandType.PLAY_STATE,
                        GeneralCommandType.PLAY_NEXT,
                        GeneralCommandType.PLAY_MEDIA_SOURCE,
                    ),
                supportsMediaControl = true,
            )
        }
    }

    override suspend fun postPlaybackStart(itemId: UUID) {
        Timber.d("Sending start $itemId")
        withContext(Dispatchers.IO) {
            jellyfinApi.playStateApi.reportPlaybackStart(
                PlaybackStartInfo(
                    itemId = itemId,
                    canSeek = true,
                    isPaused = false,
                    isMuted = false,
                    playMethod = PlayMethod.DIRECT_PLAY,
                    repeatMode = RepeatMode.REPEAT_NONE,
                    playbackOrder = PlaybackOrder.DEFAULT,
                )
            )
        }
    }

    override suspend fun postPlaybackStop(
        itemId: UUID,
        positionTicks: Long,
        playedPercentage: Int,
    ) {
        Timber.d("Sending stop $itemId")
        withContext(Dispatchers.IO) {
            when {
                playedPercentage < 10 -> {
                    database.setPlaybackPositionTicks(itemId, jellyfinApi.userId!!, 0)
                    database.setPlayed(jellyfinApi.userId!!, itemId, false)
                }
                playedPercentage > 90 -> {
                    database.setPlaybackPositionTicks(itemId, jellyfinApi.userId!!, 0)
                    database.setPlayed(jellyfinApi.userId!!, itemId, true)
                }
                else -> {
                    database.setPlaybackPositionTicks(itemId, jellyfinApi.userId!!, positionTicks)
                    database.setPlayed(jellyfinApi.userId!!, itemId, false)
                }
            }
            try {
                jellyfinApi.playStateApi.reportPlaybackStopped(
                    PlaybackStopInfo(itemId = itemId, positionTicks = positionTicks, failed = false)
                )
            } catch (_: Exception) {
                database.setUserDataToBeSynced(jellyfinApi.userId!!, itemId, true)
            }
        }
    }

    override suspend fun postPlaybackProgress(
        itemId: UUID,
        positionTicks: Long,
        isPaused: Boolean,
    ) {
        Timber.d("Posting progress of $itemId, position: $positionTicks")
        withContext(Dispatchers.IO) {
            database.setPlaybackPositionTicks(itemId, jellyfinApi.userId!!, positionTicks)
            try {
                jellyfinApi.playStateApi.reportPlaybackProgress(
                    PlaybackProgressInfo(
                        itemId = itemId,
                        canSeek = true,
                        isPaused = isPaused,
                        isMuted = false,
                        playMethod = PlayMethod.DIRECT_PLAY,
                        repeatMode = RepeatMode.REPEAT_NONE,
                        playbackOrder = PlaybackOrder.DEFAULT,
                        positionTicks = positionTicks,
                    )
                )
            } catch (_: Exception) {
                database.setUserDataToBeSynced(jellyfinApi.userId!!, itemId, true)
            }
        }
    }

    override suspend fun markAsFavorite(itemId: UUID) {
        withContext(Dispatchers.IO) {
            database.setFavorite(jellyfinApi.userId!!, itemId, true)
            try {
                jellyfinApi.userLibraryApi.markFavoriteItem(itemId)
            } catch (_: Exception) {
                database.setUserDataToBeSynced(jellyfinApi.userId!!, itemId, true)
            }
        }
    }

    override suspend fun unmarkAsFavorite(itemId: UUID) {
        withContext(Dispatchers.IO) {
            database.setFavorite(jellyfinApi.userId!!, itemId, false)
            try {
                jellyfinApi.userLibraryApi.unmarkFavoriteItem(itemId)
            } catch (_: Exception) {
                database.setUserDataToBeSynced(jellyfinApi.userId!!, itemId, true)
            }
        }
    }

    override suspend fun markAsPlayed(itemId: UUID) {
        withContext(Dispatchers.IO) {
            database.setPlayed(jellyfinApi.userId!!, itemId, true)
            try {
                jellyfinApi.playStateApi.markPlayedItem(itemId)
            } catch (_: Exception) {
                database.setUserDataToBeSynced(jellyfinApi.userId!!, itemId, true)
            }
        }
    }

    override suspend fun markAsUnplayed(itemId: UUID) {
        withContext(Dispatchers.IO) {
            database.setPlayed(jellyfinApi.userId!!, itemId, false)
            try {
                jellyfinApi.playStateApi.markUnplayedItem(itemId)
            } catch (_: Exception) {
                database.setUserDataToBeSynced(jellyfinApi.userId!!, itemId, true)
            }
        }
    }

    override fun getBaseUrl() = jellyfinApi.api.baseUrl.orEmpty()

    override suspend fun updateDeviceName(name: String) {
        withContext(Dispatchers.IO) {
            jellyfinApi.jellyfin.deviceInfo?.id?.let { id ->
                jellyfinApi.devicesApi.updateDeviceOptions(
                    id,
                    DeviceOptionsDto(0, customName = name),
                )
            }
        }
    }

    override suspend fun getUserConfiguration(): UserConfiguration =
        withContext(Dispatchers.IO) { jellyfinApi.userApi.getCurrentUser().content.configuration!! }

    override suspend fun getDownloads(): List<FindroidItem> =
        withContext(Dispatchers.IO) {
            val items = mutableListOf<FindroidItem>()
            items.addAll(
                database
                    .getMoviesByServerId(appPreferences.getValue(appPreferences.currentServer)!!)
                    .map { it.toFindroidMovie(database, jellyfinApi.userId!!) }
            )
            items.addAll(
                database
                    .getShowsByServerId(appPreferences.getValue(appPreferences.currentServer)!!)
                    .map { it.toFindroidShow(database, jellyfinApi.userId!!) }
            )
            items
        }

    override suspend fun searchRemoteSubtitles(
        itemId: UUID,
        language: String?,
    ): List<RemoteSubtitleDto> =
        withContext(Dispatchers.IO) { subtitleApi.searchRemoteSubtitles(itemId, language) }

    override suspend fun downloadRemoteSubtitle(itemId: UUID, remoteSubtitleId: String): Boolean =
        withContext(Dispatchers.IO) { subtitleApi.downloadRemoteSubtitle(itemId, remoteSubtitleId) }

    override fun getUserId(): UUID {
        return jellyfinApi.userId ?: UUID(0L, 0L)
    }

    override fun getPreferredAudioLanguage(): String? {
        val userId = getUserId()
        return if (userId != UUID(0L, 0L)) {
            appPreferences.getValue(appPreferences.getUserPreferredAudioLanguage(userId))
        } else {
            appPreferences.getValue(appPreferences.preferredAudioLanguage)
        }
    }

    override fun getPreferredSubtitleLanguage(): String? {
        val userId = getUserId()
        return if (userId != UUID(0L, 0L)) {
            appPreferences.getValue(appPreferences.getUserPreferredSubtitleLanguage(userId))
        } else {
            appPreferences.getValue(appPreferences.preferredSubtitleLanguage)
        }
    }
}
