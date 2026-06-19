<<<<<<< HEAD
package com.vdc.tv.ui.components
=======
package dev.jdtech.jellyfin.ui.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(
        color = Color.White,
        strokeWidth = 2.dp,
        trackColor = Color.Transparent,
        modifier = Modifier.size(32.dp),
    )
}
