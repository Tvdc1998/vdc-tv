<<<<<<< HEAD
package com.vdc.tv.presentation.film.components
=======
package dev.jdtech.jellyfin.presentation.film.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import com.vdc.tv.core.presentation.dummy.dummyMovies
import com.vdc.tv.film.presentation.home.HomeAction
import com.vdc.tv.models.FindroidItem
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.presentation.theme.spacings
=======
import dev.jdtech.jellyfin.core.presentation.dummy.dummyMovies
import dev.jdtech.jellyfin.film.presentation.home.HomeAction
import dev.jdtech.jellyfin.models.FindroidItem
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
import dev.jdtech.jellyfin.presentation.theme.spacings
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import kotlinx.coroutines.delay

private val dynamicPageSize =
    object : PageSize {
        override fun Density.calculateMainAxisPageSize(availableSpace: Int, pageSpacing: Int): Int {
            val nPages =
                when {
                    availableSpace.toDp() >= 840.dp -> 3
                    availableSpace.toDp() >= 600.dp -> 2
                    else -> 1
                }

            return (availableSpace - (nPages - 1) * pageSpacing) / nPages
        }
    }

@Composable
fun HomeCarousel(
    items: List<FindroidItem>,
    itemsPadding: PaddingValues,
    onAction: (HomeAction) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    val pagerIsDragged by pagerState.interactionSource.collectIsDraggedAsState()

    val autoScrollDelay = 5000L

    if (!pagerIsDragged) {
        LaunchedEffect(pagerState) {
            while (true) {
                delay(autoScrollDelay)
                val nextPage =
                    if (pagerState.canScrollForward) {
                        pagerState.currentPage + 1
                    } else {
                        0
                    }
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        contentPadding = itemsPadding,
        pageSize = dynamicPageSize,
        pageSpacing = MaterialTheme.spacings.medium,
    ) { page ->
        val item = items[page]
        HomeCarouselItem(item = item, onAction = onAction)
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeCarouselPreview() {
    FindroidTheme {
        HomeCarousel(
            items = dummyMovies,
            itemsPadding = PaddingValues(horizontal = 0.dp),
            onAction = {},
        )
    }
}
