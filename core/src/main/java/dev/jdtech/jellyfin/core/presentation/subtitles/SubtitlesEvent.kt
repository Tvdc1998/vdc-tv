<<<<<<< HEAD
package com.vdc.tv.core.presentation.subtitles
=======
package dev.jdtech.jellyfin.core.presentation.subtitles
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface SubtitlesEvent {
    data object SubtitleDownloading : SubtitlesEvent
    data class SubtitleDownloaded(val url: String, val title: String?, val language: String?) : SubtitlesEvent
    data class Error(val message: String) : SubtitlesEvent
}
