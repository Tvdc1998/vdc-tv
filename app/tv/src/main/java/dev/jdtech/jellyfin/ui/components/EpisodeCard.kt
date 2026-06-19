<<<<<<< HEAD
package com.vdc.tv.ui.components
=======
package dev.jdtech.jellyfin.ui.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ClickableSurfaceScale
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
<<<<<<< HEAD
import com.vdc.tv.core.presentation.dummy.dummyEpisode
import com.vdc.tv.models.FindroidEpisode
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.presentation.theme.spacings
=======
import dev.jdtech.jellyfin.core.presentation.dummy.dummyEpisode
import dev.jdtech.jellyfin.models.FindroidEpisode
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
import dev.jdtech.jellyfin.presentation.theme.spacings
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun EpisodeCard(episode: FindroidEpisode, onClick: (FindroidEpisode) -> Unit) {
    Surface(
        onClick = { onClick(episode) },
        shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(10.dp)),
        colors =
            ClickableSurfaceDefaults.colors(
                containerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
            ),
        border =
            ClickableSurfaceDefaults.border(
                focusedBorder =
                    Border(BorderStroke(4.dp, Color.White), shape = RoundedCornerShape(10.dp))
            ),
        scale = ClickableSurfaceScale.None,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(modifier = Modifier.padding(MaterialTheme.spacings.small)) {
            Box(modifier = Modifier.width(160.dp)) {
                ItemPoster(
                    item = episode,
                    direction = Direction.HORIZONTAL,
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                )
                ProgressBadge(
                    item = episode,
                    modifier =
                        Modifier.align(Alignment.TopEnd)
                            .padding(PaddingValues(MaterialTheme.spacings.small)),
                )
            }
            Spacer(modifier = Modifier.width(MaterialTheme.spacings.medium))
            Column {
                Text(
                    text =
                        stringResource(
<<<<<<< HEAD
                            id = com.vdc.tv.core.R.string.episode_name,
=======
                            id = dev.jdtech.jellyfin.core.R.string.episode_name,
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
                            episode.indexNumber,
                            episode.name,
                        ),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacings.extraSmall))
                Text(
                    text = episode.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ItemCardPreviewEpisode() {
    FindroidTheme { EpisodeCard(episode = dummyEpisode, onClick = {}) }
}
