package dev.jdtech.jellyfin.core.presentation.subtitles

import dev.jdtech.jellyfin.models.RemoteSubtitleDto

sealed interface SubtitlesAction {
    data class OnLanguageSelected(val language: String) : SubtitlesAction
    data class OnSubtitleSelected(val subtitle: RemoteSubtitleDto) : SubtitlesAction
    data object OnDismiss : SubtitlesAction
    data object OnRetry : SubtitlesAction
}
