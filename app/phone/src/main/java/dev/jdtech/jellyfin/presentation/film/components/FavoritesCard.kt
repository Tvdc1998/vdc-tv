<<<<<<< HEAD
package com.vdc.tv.presentation.film.components
=======
package dev.jdtech.jellyfin.presentation.film.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.presentation.theme.spacings
=======
import dev.jdtech.jellyfin.core.R as CoreR
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
import dev.jdtech.jellyfin.presentation.theme.spacings
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun FavoritesCard(onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedCard(onClick = onClick, modifier = modifier) {
        Row(
            modifier = Modifier.padding(MaterialTheme.spacings.medium),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(painter = painterResource(CoreR.drawable.ic_star), contentDescription = null)
            Text(text = stringResource(CoreR.string.title_favorite))
        }
    }
}

@Preview
@Composable
private fun FavoritesCardPreview() {
    FindroidTheme { FavoritesCard(onClick = {}, modifier = Modifier.width(320.dp)) }
}
