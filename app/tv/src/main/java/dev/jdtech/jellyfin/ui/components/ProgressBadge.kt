<<<<<<< HEAD
package com.vdc.tv.ui.components
=======
package dev.jdtech.jellyfin.ui.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
<<<<<<< HEAD
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.core.presentation.dummy.dummyEpisode
import com.vdc.tv.core.presentation.dummy.dummyShow
import com.vdc.tv.models.FindroidItem
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.presentation.theme.spacings
=======
import dev.jdtech.jellyfin.core.R as CoreR
import dev.jdtech.jellyfin.core.presentation.dummy.dummyEpisode
import dev.jdtech.jellyfin.core.presentation.dummy.dummyShow
import dev.jdtech.jellyfin.models.FindroidItem
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
import dev.jdtech.jellyfin.presentation.theme.spacings
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun ProgressBadge(item: FindroidItem, modifier: Modifier = Modifier) {
    if (!(!item.played && item.unplayedItemCount == null)) {
        Box(
            modifier =
                modifier
                    .height(24.dp)
                    .defaultMinSize(24.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primary)
        ) {
            when (item.played) {
                true -> {
                    Icon(
                        painter = painterResource(id = CoreR.drawable.ic_check),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(16.dp).align(Alignment.Center),
                    )
                }

                false -> {
                    Text(
                        text = item.unplayedItemCount.toString(),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium,
                        modifier =
                            Modifier.align(Alignment.Center)
                                .padding(horizontal = MaterialTheme.spacings.extraSmall),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProgressBadgePreviewWatched() {
    FindroidTheme { ProgressBadge(item = dummyEpisode) }
}

@Preview
@Composable
private fun ProgressBadgePreviewItemRemaining() {
    FindroidTheme { ProgressBadge(item = dummyShow) }
}
