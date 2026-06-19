<<<<<<< HEAD
package com.vdc.tv.presentation.film.components
=======
package dev.jdtech.jellyfin.presentation.film.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
<<<<<<< HEAD
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.models.FindroidItem
import com.vdc.tv.presentation.theme.spacings
=======
import dev.jdtech.jellyfin.core.R as CoreR
import dev.jdtech.jellyfin.models.FindroidItem
import dev.jdtech.jellyfin.presentation.theme.spacings
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID

@Composable
fun GenreDialog(
    genres: List<FindroidItem>,
    selectedGenreIds: List<UUID>,
    onToggleGenre: (UUID) -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.fillMaxWidth().heightIn(max = 540.dp),
            shape = MaterialTheme.shapes.extraLarge,
            colors =
                CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                ),
        ) {
            Column {
                Spacer(modifier = Modifier.height(MaterialTheme.spacings.default))
                Text(
                    text = stringResource(CoreR.string.genres),
                    modifier =
                        Modifier.fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacings.default),
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacings.medium))
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(genres) { genre ->
                        GenreDialogItem(
                            genre = genre,
                            isSelected = selectedGenreIds.contains(genre.id),
                            onToggle = { onToggleGenre(genre.id) },
                        )
                    }
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacings.medium))
            }
        }
    }
}

@Composable
private fun GenreDialogItem(
    genre: FindroidItem,
    isSelected: Boolean,
    onToggle: () -> Unit,
) {
    Row(
        modifier =
            Modifier.fillMaxWidth()
                .clickable { onToggle() }
                .padding(horizontal = MaterialTheme.spacings.default, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = isSelected, onCheckedChange = { onToggle() })
        Spacer(modifier = Modifier.width(MaterialTheme.spacings.medium))
        Text(text = genre.name)
    }
}
