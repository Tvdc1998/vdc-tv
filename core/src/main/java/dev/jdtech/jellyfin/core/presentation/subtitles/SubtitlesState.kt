package dev.jdtech.jellyfin.core.presentation.subtitles

import dev.jdtech.jellyfin.models.RemoteSubtitleDto

data class SubtitlesState(
    val isLoading: Boolean = false,
    val languages: List<String> = emptyList(),
    val selectedLanguage: String? = null,
    val remoteSubtitles: List<RemoteSubtitleDto> = emptyList(),
    val error: String? = null,
)
