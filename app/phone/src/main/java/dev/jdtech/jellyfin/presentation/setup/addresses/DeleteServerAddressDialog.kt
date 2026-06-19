<<<<<<< HEAD
package com.vdc.tv.presentation.setup.addresses
=======
package dev.jdtech.jellyfin.presentation.setup.addresses
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
<<<<<<< HEAD
import com.vdc.tv.setup.R as SetupR
=======
import dev.jdtech.jellyfin.setup.R as SetupR
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun DeleteServerAddressDialog(address: String, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        title = { Text(text = stringResource(SetupR.string.remove_server_address)) },
        text = {
            Text(text = stringResource(SetupR.string.remove_server_address_dialog_text, address))
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) { Text(text = stringResource(SetupR.string.confirm)) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(text = stringResource(SetupR.string.cancel)) }
        },
    )
}
