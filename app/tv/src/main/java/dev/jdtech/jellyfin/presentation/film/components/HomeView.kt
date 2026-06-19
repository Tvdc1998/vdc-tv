<<<<<<< HEAD
package com.vdc.tv.presentation.film.components
=======
package dev.jdtech.jellyfin.presentation.film.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
<<<<<<< HEAD
import com.vdc.tv.film.R as FilmR
import com.vdc.tv.film.presentation.home.HomeAction
import com.vdc.tv.models.HomeItem
import com.vdc.tv.presentation.theme.spacings
import com.vdc.tv.ui.components.Direction
import com.vdc.tv.ui.components.ItemCard
=======
import dev.jdtech.jellyfin.film.R as FilmR
import dev.jdtech.jellyfin.film.presentation.home.HomeAction
import dev.jdtech.jellyfin.models.HomeItem
import dev.jdtech.jellyfin.presentation.theme.spacings
import dev.jdtech.jellyfin.ui.components.Direction
import dev.jdtech.jellyfin.ui.components.ItemCard
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun HomeView(
    view: HomeItem.ViewItem,
    itemsPadding: PaddingValues,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = FilmR.string.latest_library, view.view.name),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(itemsPadding),
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacings.medium))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.default),
            contentPadding = itemsPadding,
        ) {
            items(view.view.items, key = { it.id }) { item ->
                ItemCard(
                    item = item,
                    direction = Direction.VERTICAL,
                    onClick = { onAction(HomeAction.OnItemClick(it)) },
                )
            }
        }
    }
}
