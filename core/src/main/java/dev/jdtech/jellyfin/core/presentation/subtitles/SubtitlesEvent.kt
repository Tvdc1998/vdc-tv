package dev.jdtech.jellyfin.core.presentation.subtitles

sealed interface SubtitlesEvent {
    data object SubtitleDownloading : SubtitlesEvent
    data class SubtitleDownloaded(val url: String, val title: String?, val language: String?) : SubtitlesEvent
    data class Error(val message: String) : SubtitlesEvent
}
