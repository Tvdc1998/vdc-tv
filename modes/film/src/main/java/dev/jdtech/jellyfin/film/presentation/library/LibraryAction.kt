package com.vdc.tv.film.presentation.library

import com.vdc.tv.models.FindroidItem
import com.vdc.tv.models.SortBy
import com.vdc.tv.models.SortOrder
import org.jellyfin.sdk.model.api.ItemFilter
import java.util.UUID

sealed interface LibraryAction {
    data class OnItemClick(val item: FindroidItem) : LibraryAction

    data object OnBackClick : LibraryAction

    data class ChangeSorting(val sortBy: SortBy, val sortOrder: SortOrder) : LibraryAction

    data class ToggleFilter(val filter: ItemFilter) : LibraryAction

    data class ToggleGenre(val genreId: UUID) : LibraryAction

    data object ToggleViewType : LibraryAction
}
