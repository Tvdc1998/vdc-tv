<<<<<<< HEAD
package com.vdc.tv.presentation.film.components
=======
package dev.jdtech.jellyfin.presentation.film.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import com.vdc.tv.film.presentation.home.HomeAction
import com.vdc.tv.film.presentation.library.LibraryViewType
import com.vdc.tv.models.HomeSection
import com.vdc.tv.presentation.theme.spacings
=======
import dev.jdtech.jellyfin.film.presentation.home.HomeAction
import dev.jdtech.jellyfin.film.presentation.library.LibraryViewType
import dev.jdtech.jellyfin.models.HomeSection
import dev.jdtech.jellyfin.presentation.theme.spacings
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun HomeSection(
    section: HomeSection,
    itemsPadding: PaddingValues,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
    viewType: LibraryViewType = LibraryViewType.POSTER,
) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth().height(42.dp).padding(itemsPadding)) {
            Text(
                text = section.name.asString(),
                modifier = Modifier.align(Alignment.CenterStart),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacings.extraSmall))
        LazyRow(
            contentPadding = itemsPadding,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.default),
        ) {
            items(section.items, key = { it.id }) { item ->
                ItemCard(
                    item = item,
                    direction = Direction.HORIZONTAL,
                    onClick = { onAction(HomeAction.OnItemClick(item)) },
                    viewType = viewType,
                )
            }
        }
    }
}
