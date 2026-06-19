<<<<<<< HEAD
package com.vdc.tv.presentation.film.components
=======
package dev.jdtech.jellyfin.presentation.film.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.text.format.Formatter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
<<<<<<< HEAD
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.models.VideoMetadata
import com.vdc.tv.presentation.theme.spacings
=======
import dev.jdtech.jellyfin.core.R as CoreR
import dev.jdtech.jellyfin.models.VideoMetadata
import dev.jdtech.jellyfin.presentation.theme.spacings
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun ExtraInfoText(videoMetadata: VideoMetadata) {
    val context = LocalContext.current

    Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.small)) {
        Text(
            text =
                "${stringResource(CoreR.string.size)}: ${Formatter.formatFileSize(context, videoMetadata.size)}",
            style = MaterialTheme.typography.bodyMedium,
        )
        if (videoMetadata.videoTracks.isNotEmpty()) {
            Text(
                text =
                    "${stringResource(CoreR.string.video)}: ${videoMetadata.videoTracks.joinToString { it }}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        if (videoMetadata.audioTracks.isNotEmpty()) {
            Text(
                text =
                    "${stringResource(CoreR.string.audio)}: ${videoMetadata.audioTracks.joinToString { it }}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        if (videoMetadata.subtitleTracks.isNotEmpty()) {
            Text(
                text =
                    "${stringResource(CoreR.string.subtitle)}: ${videoMetadata.subtitleTracks.joinToString { it }}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
