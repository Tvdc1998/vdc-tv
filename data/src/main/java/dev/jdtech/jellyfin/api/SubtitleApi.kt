package com.vdc.tv.api

import com.vdc.tv.models.RemoteSubtitleDto
import java.util.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber

class SubtitleApi(private val jellyfinApi: JellyfinApi) {

    private val client = OkHttpClient()
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun searchRemoteSubtitles(itemId: UUID, language: String? = null): List<RemoteSubtitleDto> =
        withContext(Dispatchers.IO) {
            val baseUrl = jellyfinApi.api.baseUrl ?: return@withContext emptyList()
            val token = jellyfinApi.accessToken ?: return@withContext emptyList()

            if (language == null) return@withContext emptyList()

            val url = "$baseUrl/Items/$itemId/RemoteSearch/Subtitles/$language"

            Timber.d("Searching subtitles: $url")
            val request =
                Request.Builder()
                    .url(url)
                    .addHeader("X-Emby-Token", token)
                    .get()
                    .build()

            try {
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        Timber.e("Failed to search subtitles: ${response.code}")
                        return@withContext emptyList()
                    }
                    val body = response.body.string()
                    json.decodeFromString<List<RemoteSubtitleDto>>(body)
                }
            } catch (e: Exception) {
                Timber.e(e, "Error searching subtitles")
                emptyList()
            }
        }

    suspend fun downloadRemoteSubtitle(itemId: UUID, remoteSubtitleId: String): Boolean =
        withContext(Dispatchers.IO) {
            val baseUrl = jellyfinApi.api.baseUrl ?: return@withContext false
            val token = jellyfinApi.accessToken ?: return@withContext false

            val url = "$baseUrl/Items/$itemId/RemoteSearch/Subtitles/$remoteSubtitleId"

            val request =
                Request.Builder()
                    .url(url)
                    .addHeader("X-Emby-Token", token)
                    .post("".toRequestBody("application/json".toMediaType()))
                    .build()

            try {
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        Timber.e("Failed to download subtitle: ${response.code}")
                        false
                    } else {
                        true
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Error downloading subtitle")
                false
            }
        }
}
