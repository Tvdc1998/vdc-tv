<<<<<<< HEAD
package com.vdc.tv.presentation.film.components
=======
package dev.jdtech.jellyfin.presentation.film.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.presentation.theme.FindroidTheme
=======
import dev.jdtech.jellyfin.core.R as CoreR
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun PlayedBadge(modifier: Modifier = Modifier) {
    BaseBadge(modifier = modifier) {
        Icon(
            painter = painterResource(CoreR.drawable.ic_check),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(16.dp).align(Alignment.Center),
        )
    }
}

@Composable
@Preview
private fun PlayedBadgePreview() {
    FindroidTheme { PlayedBadge() }
}
