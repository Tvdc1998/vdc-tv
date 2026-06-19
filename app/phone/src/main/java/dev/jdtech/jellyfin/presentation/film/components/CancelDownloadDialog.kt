<<<<<<< HEAD
package com.vdc.tv.presentation.film.components
=======
package dev.jdtech.jellyfin.presentation.film.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
<<<<<<< HEAD
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.presentation.theme.FindroidTheme
=======
import dev.jdtech.jellyfin.core.R as CoreR
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun CancelDownloadDialog(onCancel: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        title = { Text(text = stringResource(CoreR.string.cancel_download)) },
        text = { Text(text = stringResource(CoreR.string.cancel_download_message)) },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onCancel) {
                Text(text = stringResource(CoreR.string.stop_download))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(text = stringResource(CoreR.string.cancel)) }
        },
    )
}

@Composable
@Preview
private fun CancelDownloadDialogPreview() {
    FindroidTheme { CancelDownloadDialog(onCancel = {}, onDismiss = {}) }
}
