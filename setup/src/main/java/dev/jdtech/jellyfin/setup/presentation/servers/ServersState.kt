<<<<<<< HEAD
package com.vdc.tv.setup.presentation.servers

import com.vdc.tv.models.ServerWithAddresses
=======
package dev.jdtech.jellyfin.setup.presentation.servers

import dev.jdtech.jellyfin.models.ServerWithAddresses
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class ServersState(val servers: List<ServerWithAddresses> = emptyList())
