package com.vdc.tv.setup.presentation.servers

import com.vdc.tv.models.ServerWithAddresses

data class ServersState(val servers: List<ServerWithAddresses> = emptyList())
