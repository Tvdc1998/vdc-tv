<<<<<<< HEAD
package com.vdc.tv.presentation.film.components
=======
package dev.jdtech.jellyfin.presentation.film.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import com.vdc.tv.core.presentation.dummy.dummyMovie
import com.vdc.tv.models.FindroidItem
import com.vdc.tv.presentation.theme.FindroidTheme
=======
import dev.jdtech.jellyfin.core.presentation.dummy.dummyMovie
import dev.jdtech.jellyfin.models.FindroidItem
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun ProgressBar(item: FindroidItem, width: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box(
            modifier =
                Modifier.height(4.dp)
                    .width(
                        item.playbackPositionTicks
                            .div(item.runtimeTicks.toFloat())
                            .times(width - 16)
                            .dp
                    )
                    .clip(MaterialTheme.shapes.extraSmall)
                    .background(MaterialTheme.colorScheme.primary)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressBarPreview() {
    FindroidTheme { ProgressBar(item = dummyMovie, width = 142) }
}
