package dev.jdtech.jellyfin.presentation.film.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
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

    // Ugly workaround to append the files directory when loading local images
    if (imageUri?.scheme == null) {
        imageUri =
            Uri.Builder()
                .appendEncodedPath("${context.filesDir}")
                .appendEncodedPath(imageUri?.path)
                .build()
    }

    AsyncImage(
        model = imageUri,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier =
            modifier
                .aspectRatio(if (direction == Direction.HORIZONTAL) 1.77f else 0.66f)
                .background(MaterialTheme.colorScheme.surfaceContainer),
    )
}
