<<<<<<< HEAD
package com.vdc.tv.presentation.film.components
=======
package dev.jdtech.jellyfin.presentation.film.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import com.vdc.tv.film.presentation.collection.CollectionAction
import com.vdc.tv.models.CollectionSection
import com.vdc.tv.models.FindroidEpisode
import com.vdc.tv.presentation.theme.spacings
import com.vdc.tv.presentation.utils.GridCellsAdaptiveWithMinColumns
=======
import dev.jdtech.jellyfin.film.presentation.collection.CollectionAction
import dev.jdtech.jellyfin.models.CollectionSection
import dev.jdtech.jellyfin.models.FindroidEpisode
import dev.jdtech.jellyfin.presentation.theme.spacings
import dev.jdtech.jellyfin.presentation.utils.GridCellsAdaptiveWithMinColumns
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun CollectionGrid(
    sections: List<CollectionSection>,
    innerPadding: PaddingValues,
    onAction: (CollectionAction) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCellsAdaptiveWithMinColumns(minSize = 160.dp, minColumns = 2),
        modifier = Modifier.padding(innerPadding).fillMaxSize(),
        contentPadding = PaddingValues(all = MaterialTheme.spacings.default),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.default),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.default),
    ) {
        sections.forEach { section ->
            stickyHeader {
                Card {
                    Text(
                        text = section.name.asString(),
                        modifier =
                            Modifier.padding(
                                horizontal = MaterialTheme.spacings.medium,
                                vertical = MaterialTheme.spacings.medium,
                            ),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
            items(items = section.items, key = { it.id }) { item ->
                ItemCard(
                    item = item,
                    direction =
                        if (item is FindroidEpisode) Direction.HORIZONTAL else Direction.VERTICAL,
                    onClick = { onAction(CollectionAction.OnItemClick(item)) },
                    modifier = Modifier.animateItem(),
                )
            }
        }
    }
}
