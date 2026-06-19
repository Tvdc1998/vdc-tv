package com.vdc.tv.core.presentation.subtitles

import com.vdc.tv.models.RemoteSubtitleDto

sealed interface SubtitlesAction {
    data class OnLanguageSelected(val language: String) : SubtitlesAction
    data class OnSubtitleSelected(val subtitle: RemoteSubtitleDto) : SubtitlesAction
    data object OnDismiss : SubtitlesAction
    data object OnRetry : SubtitlesAction
}
