<<<<<<< HEAD
package com.vdc.tv.film.presentation.library

import androidx.paging.PagingData
import com.vdc.tv.models.FindroidItem
import com.vdc.tv.models.SortBy
import com.vdc.tv.models.SortOrder
=======
package dev.jdtech.jellyfin.film.presentation.library

import androidx.paging.PagingData
import dev.jdtech.jellyfin.models.FindroidItem
import dev.jdtech.jellyfin.models.SortBy
import dev.jdtech.jellyfin.models.SortOrder
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import org.jellyfin.sdk.model.api.ItemFilter
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class LibraryState(
    val items: Flow<PagingData<FindroidItem>> = emptyFlow(),
    val sortBy: SortBy = SortBy.NAME,
    val sortOrder: SortOrder = SortOrder.ASCENDING,
    val filters: List<ItemFilter> = emptyList(),
    val genres: List<FindroidItem> = emptyList(),
    val genreIds: List<UUID> = emptyList(),
    val viewType: LibraryViewType = LibraryViewType.POSTER,
    val isLoading: Boolean = false,
    val error: Exception? = null,
)

enum class LibraryViewType {
    POSTER,
    THUMBNAIL
}
