<<<<<<< HEAD
package com.vdc.tv.presentation.film
=======
package dev.jdtech.jellyfin.presentation.film
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.tv.material3.MaterialTheme
<<<<<<< HEAD
import com.vdc.tv.core.presentation.dummy.dummyCollections
import com.vdc.tv.film.presentation.media.MediaAction
import com.vdc.tv.film.presentation.media.MediaState
import com.vdc.tv.film.presentation.media.MediaViewModel
import com.vdc.tv.models.CollectionType
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.presentation.theme.spacings
import com.vdc.tv.ui.components.Direction
import com.vdc.tv.ui.components.ItemCard
=======
import dev.jdtech.jellyfin.core.presentation.dummy.dummyCollections
import dev.jdtech.jellyfin.film.presentation.media.MediaAction
import dev.jdtech.jellyfin.film.presentation.media.MediaState
import dev.jdtech.jellyfin.film.presentation.media.MediaViewModel
import dev.jdtech.jellyfin.models.CollectionType
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
import dev.jdtech.jellyfin.presentation.theme.spacings
import dev.jdtech.jellyfin.ui.components.Direction
import dev.jdtech.jellyfin.ui.components.ItemCard
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID

@Composable
fun MediaScreen(
    navigateToLibrary: (libraryId: UUID, libraryName: String, libraryType: CollectionType) -> Unit,
    isLoading: (Boolean) -> Unit,
    viewModel: MediaViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(true) { viewModel.loadData() }

    LaunchedEffect(state.isLoading) { isLoading(state.isLoading) }

    LibrariesScreenLayout(
        state = state,
        onAction = { action ->
            when (action) {
                is MediaAction.OnItemClick -> {
                    navigateToLibrary(action.item.id, action.item.name, action.item.type)
                }
                else -> Unit
            }
            viewModel.onAction(action)
        },
    )
}

@Composable
private fun LibrariesScreenLayout(state: MediaState, onAction: (MediaAction) -> Unit) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(state.libraries) { focusRequester.requestFocus() }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.large),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.large),
        contentPadding =
            PaddingValues(
                start = MaterialTheme.spacings.large,
                top = MaterialTheme.spacings.small,
                end = MaterialTheme.spacings.large,
                bottom = MaterialTheme.spacings.large,
            ),
        modifier = Modifier.focusRequester(focusRequester),
    ) {
        items(state.libraries, key = { it.id }) { library ->
            ItemCard(
                item = library,
                direction = Direction.HORIZONTAL,
                onClick = { onAction(MediaAction.OnItemClick(library)) },
            )
        }
    }
}

@Preview(device = "id:tv_1080p")
@Composable
private fun LibrariesScreenLayoutPreview() {
    FindroidTheme {
        LibrariesScreenLayout(state = MediaState(libraries = dummyCollections), onAction = {})
    }
}
