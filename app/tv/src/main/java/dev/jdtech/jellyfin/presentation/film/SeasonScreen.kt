<<<<<<< HEAD
package com.vdc.tv.presentation.film
=======
package dev.jdtech.jellyfin.presentation.film
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
<<<<<<< HEAD
import com.vdc.tv.core.presentation.dummy.dummyEpisodes
import com.vdc.tv.core.presentation.dummy.dummySeason
import com.vdc.tv.film.presentation.season.SeasonAction
import com.vdc.tv.film.presentation.season.SeasonState
import com.vdc.tv.film.presentation.season.SeasonViewModel
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.presentation.theme.spacings
import com.vdc.tv.ui.components.EpisodeCard
=======
import dev.jdtech.jellyfin.core.presentation.dummy.dummyEpisodes
import dev.jdtech.jellyfin.core.presentation.dummy.dummySeason
import dev.jdtech.jellyfin.film.presentation.season.SeasonAction
import dev.jdtech.jellyfin.film.presentation.season.SeasonState
import dev.jdtech.jellyfin.film.presentation.season.SeasonViewModel
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
import dev.jdtech.jellyfin.presentation.theme.spacings
import dev.jdtech.jellyfin.ui.components.EpisodeCard
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID

@Composable
fun SeasonScreen(
    seasonId: UUID,
    navigateToPlayer: (itemId: UUID) -> Unit,
    viewModel: SeasonViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(true) { viewModel.loadSeason(seasonId = seasonId) }

    SeasonScreenLayout(
        state = state,
        onAction = { action ->
            when (action) {
                is SeasonAction.NavigateToItem -> navigateToPlayer(action.item.id)
                else -> Unit
            }
        },
    )
}

@Composable
private fun SeasonScreenLayout(state: SeasonState, onAction: (SeasonAction) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        state.season?.let { season ->
            Row(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier =
                        Modifier.weight(1f)
                            .padding(
                                start = MaterialTheme.spacings.extraLarge,
                                top = MaterialTheme.spacings.large,
                                end = MaterialTheme.spacings.large,
                            )
                ) {
                    Text(text = season.name, style = MaterialTheme.typography.displayMedium)
                    Text(text = season.seriesName, style = MaterialTheme.typography.headlineMedium)
                }
                LazyColumn(
                    contentPadding =
                        PaddingValues(
                            top = MaterialTheme.spacings.large,
                            bottom = MaterialTheme.spacings.large,
                        ),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.medium),
                    modifier = Modifier.weight(2f).padding(end = MaterialTheme.spacings.extraLarge),
                ) {
                    items(state.episodes) { episode ->
                        EpisodeCard(
                            episode = episode,
                            onClick = { onAction(SeasonAction.NavigateToItem(episode)) },
                        )
                    }
                }
            }
        } ?: run { CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) }
    }
}

@Preview(device = "id:tv_1080p")
@Composable
private fun SeasonScreenLayoutPreview() {
    FindroidTheme {
        SeasonScreenLayout(
            state = SeasonState(season = dummySeason, episodes = dummyEpisodes),
            onAction = {},
        )
    }
}
