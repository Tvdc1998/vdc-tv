package dev.jdtech.jellyfin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.tv.material3.MaterialTheme
import coil3.compose.AsyncImage
import dev.jdtech.jellyfin.film.presentation.library.LibraryViewType
import dev.jdtech.jellyfin.models.FindroidEpisode
import dev.jdtech.jellyfin.models.FindroidItem
import dev.jdtech.jellyfin.models.FindroidMovie

enum class Direction {
    HORIZONTAL,
    VERTICAL,
}

@Composable
fun ItemPoster(item: FindroidItem, direction: Direction, modifier: Modifier = Modifier, viewType: LibraryViewType = LibraryViewType.POSTER) {
    var imageUri =
        when {
            viewType == LibraryViewType.THUMBNAIL && item.images.thumb != null -> item.images.thumb
            item.images.primary != null -> item.images.primary
            item.images.thumb != null -> item.images.thumb
            item.images.backdrop != null -> item.images.backdrop
            else -> null
        }

    if (viewType != LibraryViewType.THUMBNAIL) {
        when (direction) {
            Direction.HORIZONTAL -> {
                if (item is FindroidMovie && item.images.backdrop != null) {
                    imageUri = item.images.backdrop
                }
            }
            Direction.VERTICAL -> {
                when (item) {
                    is FindroidEpisode -> {
                        if (item.images.showPrimary != null) {
                            imageUri = item.images.showPrimary
                        }
                    }
                }
            }
        }
    }

    AsyncImage(
        model = imageUri,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier =
            modifier
                .fillMaxWidth()
                .aspectRatio(if (direction == Direction.HORIZONTAL) 1.77f else 0.66f)
                .background(MaterialTheme.colorScheme.surface),
    )
}
