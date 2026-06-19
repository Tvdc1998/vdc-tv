<<<<<<< HEAD
package com.vdc.tv.presentation.setup.components
=======
package dev.jdtech.jellyfin.presentation.setup.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
fun DiscoveredServerItem(name: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Column(modifier = modifier.width(64.dp)) {
        Card(onClick = onClick, modifier = Modifier.size(64.dp)) {
            Box(modifier = Modifier.fillMaxSize()) {
                Icon(
                    painter = painterResource(CoreR.drawable.ic_server),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
@Preview
private fun DiscoveredServerItemPreview() {
    FindroidTheme { DiscoveredServerItem(name = "Jellyfin Server") }
}
