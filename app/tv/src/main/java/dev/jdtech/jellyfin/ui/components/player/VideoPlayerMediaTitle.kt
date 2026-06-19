<<<<<<< HEAD
package com.vdc.tv.ui.components.player
=======
package dev.jdtech.jellyfin.ui.components.player
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
<<<<<<< HEAD
import com.vdc.tv.presentation.theme.FindroidTheme
=======
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun VideoPlayerMediaTitle(title: String, subtitle: String?) {
    Column {
        Text(text = title, style = MaterialTheme.typography.headlineMedium, color = Color.White)
        if (subtitle != null) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White.copy(alpha = .75f),
            )
        }
    }
}

@Preview
@Composable
private fun VideoPlayerMediaTitlePreview() {
    FindroidTheme {
        VideoPlayerMediaTitle(title = "S1:E23 - Handler One", subtitle = "86 EIGHTY-SIX")
    }
}
