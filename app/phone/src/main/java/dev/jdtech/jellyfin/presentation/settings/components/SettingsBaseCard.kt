<<<<<<< HEAD
package com.vdc.tv.presentation.settings.components
=======
package dev.jdtech.jellyfin.presentation.settings.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
<<<<<<< HEAD
import com.vdc.tv.settings.presentation.models.Preference
=======
import dev.jdtech.jellyfin.settings.presentation.models.Preference
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun SettingsBaseCard(
    preference: Preference,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val contentColor =
        contentColorFor(MaterialTheme.colorScheme.surface)
            .copy(alpha = if (preference.enabled) 1.0f else 0.38f)
    Surface(
        onClick = onClick,
        modifier = modifier,
        enabled = preference.enabled,
        color = Color.Transparent,
        contentColor = contentColor,
    ) {
        content()
    }
}
