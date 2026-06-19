<<<<<<< HEAD
package com.vdc.tv.presentation.film
=======
package dev.jdtech.jellyfin.presentation.film
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
<<<<<<< HEAD
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.core.presentation.dummy.dummyMovies
import com.vdc.tv.film.presentation.collection.CollectionAction
import com.vdc.tv.film.presentation.collection.CollectionState
import com.vdc.tv.film.presentation.favorites.FavoritesViewModel
import com.vdc.tv.models.CollectionSection
import com.vdc.tv.models.FindroidItem
import com.vdc.tv.models.UiText
import com.vdc.tv.presentation.theme.FindroidTheme
=======
import dev.jdtech.jellyfin.core.R as CoreR
import dev.jdtech.jellyfin.core.presentation.dummy.dummyMovies
import dev.jdtech.jellyfin.film.presentation.collection.CollectionAction
import dev.jdtech.jellyfin.film.presentation.collection.CollectionState
import dev.jdtech.jellyfin.film.presentation.favorites.FavoritesViewModel
import dev.jdtech.jellyfin.models.CollectionSection
import dev.jdtech.jellyfin.models.FindroidItem
import dev.jdtech.jellyfin.models.UiText
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun FavoritesScreen(
    onItemClick: (item: FindroidItem) -> Unit,
    navigateBack: () -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) { viewModel.loadItems() }

    CollectionScreenLayout(
        collectionName = stringResource(CoreR.string.title_favorite),
        state = state,
        onAction = { action ->
            when (action) {
                is CollectionAction.OnItemClick -> onItemClick(action.item)
                is CollectionAction.OnBackClick -> navigateBack()
            }
        },
    )
}

@PreviewScreenSizes
@Composable
private fun CollectionScreenLayoutPreview() {
    FindroidTheme {
        CollectionScreenLayout(
            collectionName = "Favorites",
            state =
                CollectionState(
                    sections =
                        listOf(
                            CollectionSection(
                                id = 0,
                                name = UiText.StringResource(CoreR.string.title_favorite),
                                items = dummyMovies,
                            )
                        )
                ),
            onAction = {},
        )
    }
}
