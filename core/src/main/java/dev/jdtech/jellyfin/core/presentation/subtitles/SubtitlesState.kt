<<<<<<< HEAD
package com.vdc.tv.core.presentation.subtitles

import com.vdc.tv.models.RemoteSubtitleDto
=======
package dev.jdtech.jellyfin.core.presentation.subtitles

import dev.jdtech.jellyfin.models.RemoteSubtitleDto
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class SubtitlesState(
    val isLoading: Boolean = false,
    val languages: List<String> = emptyList(),
    val selectedLanguage: String? = null,
    val remoteSubtitles: List<RemoteSubtitleDto> = emptyList(),
    val error: String? = null,
)
