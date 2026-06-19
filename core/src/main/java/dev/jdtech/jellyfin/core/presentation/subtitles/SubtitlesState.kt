package com.vdc.tv.core.presentation.subtitles

import com.vdc.tv.models.RemoteSubtitleDto

data class SubtitlesState(
    val isLoading: Boolean = false,
    val languages: List<String> = emptyList(),
    val selectedLanguage: String? = null,
    val remoteSubtitles: List<RemoteSubtitleDto> = emptyList(),
    val error: String? = null,
)
