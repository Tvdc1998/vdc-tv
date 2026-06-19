package com.vdc.tv.presentation.setup.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.vdc.tv.api.JellyfinApi
import androidx.compose.ui.unit.dp
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.presentation.theme.spacings

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserItem(
    name: String,
    userId: String,
    primaryImageTag: String? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
) {
    val haptics = LocalHapticFeedback.current

    val imageUrl = primaryImageTag?.let { tag ->
        "${JellyfinApi.getInstance(LocalContext.current).api.baseUrl}/Users/$userId/Images/Primary?tag=$tag"
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.medium),
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            modifier
                .clip(CardDefaults.outlinedShape)
                .combinedClickable(
                    onClick = onClick,
                    onLongClick = {
                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                        onLongClick()
                    },
                ),
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceTint,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.size(48.dp),
        ) {
            Box {
                if (imageUrl != null) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Icon(
                        painter = painterResource(CoreR.drawable.ic_user),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
        Text(text = name, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
@Preview(showBackground = true)
private fun UserItemPreview() {
    FindroidTheme { UserItem(name = "Bob", userId = "123", modifier = Modifier.width(240.dp)) }
}
