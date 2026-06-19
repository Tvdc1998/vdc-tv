<<<<<<< HEAD
package com.vdc.tv.presentation.film.components
=======
package dev.jdtech.jellyfin.presentation.film.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
<<<<<<< HEAD
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.presentation.theme.spacings
import com.vdc.tv.presentation.utils.rememberSafePadding
=======
import dev.jdtech.jellyfin.core.R as CoreR
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
import dev.jdtech.jellyfin.presentation.theme.spacings
import dev.jdtech.jellyfin.presentation.utils.rememberSafePadding
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun ItemTopBar(
    hasBackButton: Boolean,
    hasHomeButton: Boolean,
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    content: @Composable (RowScope.() -> Unit) = {},
) {
    val safePadding = rememberSafePadding()

    Row(
        modifier =
            Modifier.fillMaxWidth()
                .padding(
                    start = safePadding.start + MaterialTheme.spacings.small,
                    top = safePadding.top + MaterialTheme.spacings.small,
                    end = safePadding.end + MaterialTheme.spacings.small,
                )
    ) {
        if (hasBackButton) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.alpha(0.7f),
                colors =
                    IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                    ),
            ) {
                Icon(
                    painter = painterResource(CoreR.drawable.ic_arrow_left),
                    contentDescription = null,
                )
            }
        }
        if (hasHomeButton) {
            IconButton(
                onClick = onHomeClick,
                modifier = Modifier.alpha(0.7f),
                colors =
                    IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                    ),
            ) {
                Icon(painter = painterResource(CoreR.drawable.ic_home), contentDescription = null)
            }
        }
        content()
    }
}

@Composable
@Preview(showBackground = true)
private fun ItemTopBarPreview() {
    FindroidTheme { ItemTopBar(hasBackButton = true, hasHomeButton = true) }
}
