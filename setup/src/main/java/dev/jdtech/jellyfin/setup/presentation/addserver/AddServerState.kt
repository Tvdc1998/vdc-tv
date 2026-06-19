<<<<<<< HEAD
package com.vdc.tv.setup.presentation.addserver

import com.vdc.tv.models.DiscoveredServer
import com.vdc.tv.models.UiText
=======
package dev.jdtech.jellyfin.setup.presentation.addserver

import dev.jdtech.jellyfin.models.DiscoveredServer
import dev.jdtech.jellyfin.models.UiText
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class AddServerState(
    val isLoading: Boolean = false,
    val discoveredServers: List<DiscoveredServer> = emptyList(),
    val error: Collection<UiText>? = null,
)
