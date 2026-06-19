<<<<<<< HEAD
package com.vdc.tv.core.presentation.subtitles

import com.vdc.tv.models.RemoteSubtitleDto
=======
package dev.jdtech.jellyfin.core.presentation.subtitles

import dev.jdtech.jellyfin.models.RemoteSubtitleDto
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface SubtitlesAction {
    data class OnLanguageSelected(val language: String) : SubtitlesAction
    data class OnSubtitleSelected(val subtitle: RemoteSubtitleDto) : SubtitlesAction
    data object OnDismiss : SubtitlesAction
    data object OnRetry : SubtitlesAction
}
