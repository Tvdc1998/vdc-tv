<<<<<<< HEAD
package com.vdc.tv.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vdc.tv.models.FindroidItem
import com.vdc.tv.models.SortBy
import com.vdc.tv.models.SortOrder
=======
package dev.jdtech.jellyfin.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.jdtech.jellyfin.models.FindroidItem
import dev.jdtech.jellyfin.models.SortBy
import dev.jdtech.jellyfin.models.SortOrder
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import org.jellyfin.sdk.model.api.ItemFilter
import java.util.UUID
import org.jellyfin.sdk.model.api.BaseItemKind
import timber.log.Timber

class ItemsPagingSource(
    private val jellyfinRepository: JellyfinRepository,
    private val parentId: UUID?,
    private val includeTypes: List<BaseItemKind>?,
    private val recursive: Boolean,
    private val sortBy: SortBy,
    private val sortOrder: SortOrder,
    private val filters: List<ItemFilter>? = null,
    private val genreIds: List<UUID>? = null,
) : PagingSource<Int, FindroidItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FindroidItem> {
        val position = params.key ?: 0

        Timber.d("Retrieving position: $position")

        return try {
            val items =
                jellyfinRepository.getItems(
                    parentId = parentId,
                    includeTypes = includeTypes,
                    recursive = recursive,
                    sortBy = sortBy,
                    sortOrder = sortOrder,
                    filters = filters,
                    genreIds = genreIds,
                    startIndex = position,
                    limit = params.loadSize,
                )
            LoadResult.Page(
                data = items,
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = if (items.isEmpty()) null else position + params.loadSize,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FindroidItem>): Int {
        return 0
    }
}
