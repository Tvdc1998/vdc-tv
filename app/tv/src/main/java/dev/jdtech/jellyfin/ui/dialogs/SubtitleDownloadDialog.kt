package dev.jdtech.jellyfin.ui.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ClickableSurfaceScale
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import dev.jdtech.jellyfin.core.R
import dev.jdtech.jellyfin.core.presentation.subtitles.SubtitlesAction
import dev.jdtech.jellyfin.core.presentation.subtitles.SubtitlesEvent
import dev.jdtech.jellyfin.core.presentation.subtitles.SubtitlesViewModel
import dev.jdtech.jellyfin.presentation.theme.spacings
import dev.jdtech.jellyfin.utils.ObserveAsEvents
import java.util.UUID

@Composable
fun SubtitleDownloadDialog(
    itemId: UUID,
    onSubtitleDownloaded: (url: String, title: String?, language: String?) -> Unit,
    onDismissRequest: () -> Unit,
    viewModel: SubtitlesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = androidx.compose.ui.platform.LocalContext.current

    LaunchedEffect(itemId) {
        viewModel.setItemId(itemId)
    }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is SubtitlesEvent.SubtitleDownloading -> {
                android.widget.Toast.makeText(context, context.getString(R.string.downloading_subtitle), android.widget.Toast.LENGTH_SHORT).show()
            }
            is SubtitlesEvent.SubtitleDownloaded -> {
                android.widget.Toast.makeText(context, context.getString(R.string.subtitle_downloaded), android.widget.Toast.LENGTH_SHORT).show()
                onSubtitleDownloaded(event.url, event.title, event.language)
                onDismissRequest()
            }
            is SubtitlesEvent.Error -> {
                android.widget.Toast.makeText(context, event.message, android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }

    Surface {
        Column(modifier = Modifier.padding(MaterialTheme.spacings.medium)) {
            Text(
                text = stringResource(R.string.download_subtitles),
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacings.medium))

            if (state.isLoading && state.remoteSubtitles.isEmpty() && state.languages.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                    Text(text = stringResource(R.string.searching_subtitles))
                }
            } else if (state.selectedLanguage == null) {
                LanguageList(
                    languages = state.languages,
                    onLanguageClick = { viewModel.onAction(SubtitlesAction.OnLanguageSelected(it)) }
                )
            } else {
                SubtitleList(
                    subtitles = state.remoteSubtitles.filter { it.language == state.selectedLanguage },
                    isLoading = state.isLoading,
                    onSubtitleClick = { viewModel.onAction(SubtitlesAction.OnSubtitleSelected(it)) }
                )
            }
        }
    }
}

@Composable
private fun LanguageList(
    languages: List<String>,
    onLanguageClick: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.small),
        contentPadding = PaddingValues(vertical = MaterialTheme.spacings.extraSmall),
    ) {
        item {
            Text(
                text = stringResource(R.string.select_language),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        items(languages) { language ->
            Surface(
                onClick = { onLanguageClick(language) },
                shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(4.dp)),
                colors = ClickableSurfaceDefaults.colors(
                    containerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                ),
                border = ClickableSurfaceDefaults.border(
                    focusedBorder = Border(
                        BorderStroke(4.dp, Color.White),
                        shape = RoundedCornerShape(10.dp),
                    )
                ),
                scale = ClickableSurfaceScale.None,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(MaterialTheme.spacings.small),
                ) {
                    Text(text = language, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@Composable
private fun SubtitleList(
    subtitles: List<dev.jdtech.jellyfin.models.RemoteSubtitleDto>,
    isLoading: Boolean,
    onSubtitleClick: (dev.jdtech.jellyfin.models.RemoteSubtitleDto) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.small),
        contentPadding = PaddingValues(vertical = MaterialTheme.spacings.extraSmall),
    ) {
        if (isLoading) {
            item {
                Box(modifier = Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center) {
                    Text(text = stringResource(R.string.searching_subtitles))
                }
            }
        } else if (subtitles.isEmpty()) {
            item {
                Text(text = stringResource(R.string.no_subtitles_found))
            }
        }

        items(subtitles) { subtitle ->
            Surface(
                onClick = { onSubtitleClick(subtitle) },
                shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(4.dp)),
                colors = ClickableSurfaceDefaults.colors(
                    containerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                ),
                border = ClickableSurfaceDefaults.border(
                    focusedBorder = Border(
                        BorderStroke(4.dp, Color.White),
                        shape = RoundedCornerShape(10.dp),
                    )
                ),
                scale = ClickableSurfaceScale.None,
            ) {
                Column(
                    modifier = Modifier.padding(MaterialTheme.spacings.small),
                ) {
                    Text(
                        text = subtitle.name ?: "Unknown Subtitle",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = "${subtitle.providerName ?: "Unknown"} - ${subtitle.format ?: "SRT"}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
