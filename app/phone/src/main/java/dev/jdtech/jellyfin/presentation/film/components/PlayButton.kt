package com.vdc.tv.presentation.film.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.core.presentation.dummy.dummyEpisode
import com.vdc.tv.core.presentation.dummy.dummyMovie
import com.vdc.tv.models.FindroidItem
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.presentation.theme.spacings

@Composable
fun PlayButton(
    item: FindroidItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val runtimeMinutesLeft by
        remember(item.playbackPositionTicks) {
            mutableLongStateOf((item.runtimeTicks - item.playbackPositionTicks) / 600000000)
        }

    Button(onClick = onClick, modifier = modifier, enabled = enabled) {
        Icon(painter = painterResource(CoreR.drawable.ic_play), contentDescription = null)
        Spacer(modifier = Modifier.width(MaterialTheme.spacings.small))
        Text(
            text =
                if (item.playbackPositionTicks > 0) {
                    stringResource(CoreR.string.runtime_minutes_left, runtimeMinutesLeft)
                } else {
                    stringResource(CoreR.string.play)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlayButtonMoviePreview() {
    FindroidTheme { PlayButton(item = dummyMovie, onClick = {}) }
}

@Preview(showBackground = true)
@Composable
private fun PlayButtonEpisodePreview() {
    FindroidTheme { PlayButton(item = dummyEpisode, onClick = {}) }
}
