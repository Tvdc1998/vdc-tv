package com.vdc.tv.setup.presentation.addserver

import com.vdc.tv.models.DiscoveredServer
import com.vdc.tv.models.UiText

data class AddServerState(
    val isLoading: Boolean = false,
    val discoveredServers: List<DiscoveredServer> = emptyList(),
    val error: Collection<UiText>? = null,
)
