<<<<<<< HEAD
package com.vdc.tv.presentation.film.components
=======
package dev.jdtech.jellyfin.presentation.film.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
<<<<<<< HEAD
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.core.presentation.dummy.dummyServer
import com.vdc.tv.core.presentation.dummy.dummyServerAddress
import com.vdc.tv.models.ServerWithAddresses
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.presentation.theme.spacings
import com.vdc.tv.setup.presentation.servers.ServersAction
import com.vdc.tv.setup.presentation.servers.ServersEvent
import com.vdc.tv.setup.presentation.servers.ServersState
import com.vdc.tv.setup.presentation.servers.ServersViewModel
import com.vdc.tv.utils.ObserveAsEvents
=======
import dev.jdtech.jellyfin.core.R as CoreR
import dev.jdtech.jellyfin.core.presentation.dummy.dummyServer
import dev.jdtech.jellyfin.core.presentation.dummy.dummyServerAddress
import dev.jdtech.jellyfin.models.ServerWithAddresses
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
import dev.jdtech.jellyfin.presentation.theme.spacings
import dev.jdtech.jellyfin.setup.presentation.servers.ServersAction
import dev.jdtech.jellyfin.setup.presentation.servers.ServersEvent
import dev.jdtech.jellyfin.setup.presentation.servers.ServersState
import dev.jdtech.jellyfin.setup.presentation.servers.ServersViewModel
import dev.jdtech.jellyfin.utils.ObserveAsEvents
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServerSelectionBottomSheet(
    currentServerId: String,
    onUpdate: () -> Unit,
    onManage: () -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    viewModel: ServersViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) { viewModel.loadServers() }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is ServersEvent.ServerChanged -> onUpdate()
            is ServersEvent.AddressChanged -> onUpdate()
        }
    }

    ServerSelectionBottomSheetLayout(
        currentServerId = currentServerId,
        state = state,
        onManage = onManage,
        onAction = { action -> viewModel.onAction(action) },
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ServerSelectionBottomSheetLayout(
    currentServerId: String,
    state: ServersState,
    onManage: () -> Unit,
    onAction: (action: ServersAction) -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    ModalBottomSheet(onDismissRequest = onDismissRequest, sheetState = sheetState) {
        LazyColumn(
            contentPadding =
                PaddingValues(
                    start = MaterialTheme.spacings.medium,
                    end = MaterialTheme.spacings.medium,
                    bottom = MaterialTheme.spacings.default,
                ),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.medium),
        ) {
            items(items = state.servers, key = { it.server.id }) { server ->
                ServerSelectionItem(
                    server = server,
                    selected = server.server.id == currentServerId,
                    onClick = { onAction(ServersAction.OnServerClick(server.server.id)) },
                    onClickAddress = { addressId ->
                        onAction(ServersAction.OnAddressClick(addressId = addressId))
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            item(key = "manage") {
                OutlinedButton(onClick = onManage, modifier = Modifier.fillMaxWidth()) {
                    Text(text = stringResource(CoreR.string.manage_servers))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun ServerSelectionBottomSheetPreview() {
    FindroidTheme {
        ServerSelectionBottomSheetLayout(
            currentServerId = "",
            state =
                ServersState(
                    servers =
                        listOf(
                            ServerWithAddresses(
                                server = dummyServer,
                                addresses = listOf(dummyServerAddress),
                                user = null,
                            )
                        )
                ),
            onManage = {},
            onAction = {},
            onDismissRequest = {},
            sheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded),
        )
    }
}
