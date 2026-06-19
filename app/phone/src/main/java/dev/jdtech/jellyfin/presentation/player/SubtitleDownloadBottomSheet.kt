<<<<<<< HEAD
package com.vdc.tv.presentation.player
=======
package dev.jdtech.jellyfin.presentation.player
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
<<<<<<< HEAD
import com.vdc.tv.core.R
import com.vdc.tv.core.presentation.subtitles.SubtitlesAction
import com.vdc.tv.core.presentation.subtitles.SubtitlesEvent
import com.vdc.tv.core.presentation.subtitles.SubtitlesViewModel
import com.vdc.tv.presentation.theme.spacings
import com.vdc.tv.utils.ObserveAsEvents
=======
import dev.jdtech.jellyfin.core.R
import dev.jdtech.jellyfin.core.presentation.subtitles.SubtitlesAction
import dev.jdtech.jellyfin.core.presentation.subtitles.SubtitlesEvent
import dev.jdtech.jellyfin.core.presentation.subtitles.SubtitlesViewModel
import dev.jdtech.jellyfin.presentation.theme.spacings
import dev.jdtech.jellyfin.utils.ObserveAsEvents
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubtitleDownloadBottomSheet(
    itemId: UUID,
    onSubtitleDownloaded: (url: String, title: String?, language: String?) -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    viewModel: SubtitlesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var showingLanguageList by remember { mutableStateOf(false) }

    LaunchedEffect(itemId) {
        viewModel.setItemId(itemId)
    }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is SubtitlesEvent.SubtitleDownloading -> {
                Toast.makeText(context, context.getString(R.string.downloading_subtitle), Toast.LENGTH_SHORT).show()
            }
            is SubtitlesEvent.SubtitleDownloaded -> {
                Toast.makeText(context, context.getString(R.string.subtitle_downloaded), Toast.LENGTH_SHORT).show()
                onSubtitleDownloaded(event.url, event.title, event.language)
                onDismissRequest()
            }
            is SubtitlesEvent.Error -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacings.large)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = MaterialTheme.spacings.medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.download_subtitles),
                    style = MaterialTheme.typography.headlineSmall,
                )
                IconButton(onClick = { showingLanguageList = !showingLanguageList }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_globe),
                        contentDescription = stringResource(R.string.select_language)
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = MaterialTheme.spacings.small))

            if (showingLanguageList) {
                LanguageList(
<<<<<<< HEAD
                    languages = context.resources.getStringArray(com.vdc.tv.settings.R.array.languages).toList(),
                    languageValues = context.resources.getStringArray(com.vdc.tv.settings.R.array.languages_values).toList(),
=======
                    languages = context.resources.getStringArray(dev.jdtech.jellyfin.settings.R.array.languages).toList(),
                    languageValues = context.resources.getStringArray(dev.jdtech.jellyfin.settings.R.array.languages_values).toList(),
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
                    onLanguageClick = { 
                        viewModel.onAction(SubtitlesAction.OnLanguageSelected(it))
                        showingLanguageList = false
                    }
                )
            } else if (state.isLoading && state.remoteSubtitles.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().padding(MaterialTheme.spacings.large), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (state.error != null && state.remoteSubtitles.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(MaterialTheme.spacings.large),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.medium)
                ) {
                    Text(
                        text = state.error ?: "Unknown Error",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                    Button(onClick = { viewModel.onAction(SubtitlesAction.OnRetry) }) {
                        Text(text = stringResource(R.string.retry))
                    }
                }
            } else {
                SubtitleList(
                    subtitles = state.remoteSubtitles,
                    isLoading = state.isLoading,
                    onSubtitleClick = { viewModel.onAction(SubtitlesAction.OnSubtitleSelected(it)) },
                    error = state.error,
                    onRetryClick = { viewModel.onAction(SubtitlesAction.OnRetry) }
                )
            }
        }
    }
}

@Composable
private fun LanguageList(
    languages: List<String>,
    languageValues: List<String>,
    onLanguageClick: (String) -> Unit
) {
    LazyColumn(contentPadding = PaddingValues(MaterialTheme.spacings.medium)) {
        item {
            Text(
                text = stringResource(R.string.select_language),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = MaterialTheme.spacings.small)
            )
        }
        items(languages.zip(languageValues)) { (name, value) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLanguageClick(value) }
                    .padding(vertical = MaterialTheme.spacings.medium)
            ) {
                Text(text = name, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
private fun SubtitleList(
<<<<<<< HEAD
    subtitles: List<com.vdc.tv.models.RemoteSubtitleDto>,
    isLoading: Boolean,
    onSubtitleClick: (com.vdc.tv.models.RemoteSubtitleDto) -> Unit,
=======
    subtitles: List<dev.jdtech.jellyfin.models.RemoteSubtitleDto>,
    isLoading: Boolean,
    onSubtitleClick: (dev.jdtech.jellyfin.models.RemoteSubtitleDto) -> Unit,
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
    error: String? = null,
    onRetryClick: () -> Unit = {}
) {
    LazyColumn(contentPadding = PaddingValues(MaterialTheme.spacings.medium)) {
        if (isLoading) {
            item {
                Box(modifier = Modifier.fillMaxWidth().padding(MaterialTheme.spacings.medium), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        } else if (error != null) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(MaterialTheme.spacings.medium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.small)
                ) {
                    Text(text = error, color = MaterialTheme.colorScheme.error, textAlign = TextAlign.Center)
                    Button(onClick = onRetryClick) {
                        Text(text = stringResource(R.string.retry))
                    }
                }
            }
        } else if (subtitles.isEmpty()) {
            item {
                Text(text = stringResource(R.string.no_subtitles_found), modifier = Modifier.padding(MaterialTheme.spacings.medium))
            }
        }

        items(subtitles) { subtitle ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSubtitleClick(subtitle) }
                    .padding(vertical = MaterialTheme.spacings.medium)
            ) {
                Text(
                    text = subtitle.name ?: "Unknown Subtitle",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${subtitle.providerName ?: "Unknown"} - ${subtitle.format ?: "SRT"}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
