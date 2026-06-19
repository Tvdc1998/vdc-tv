<<<<<<< HEAD
package com.vdc.tv.core.presentation.dummy

import com.vdc.tv.models.DiscoveredServer
import com.vdc.tv.models.Server
import com.vdc.tv.models.ServerAddress
=======
package dev.jdtech.jellyfin.core.presentation.dummy

import dev.jdtech.jellyfin.models.DiscoveredServer
import dev.jdtech.jellyfin.models.Server
import dev.jdtech.jellyfin.models.ServerAddress
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID

val dummyDiscoveredServer =
    DiscoveredServer(id = "", name = "Demo server", address = "https://demo.jellyfin.org/stable")

val dummyServer =
    Server(
        id = "",
        name = "Demo server",
        currentServerAddressId = UUID.fromString("6f048d8b-aab4-4c97-9b05-8e7de4e6d604"),
        currentUserId = UUID.randomUUID(),
    )

val dummyServerAddress =
    ServerAddress(
        id = UUID.fromString("6f048d8b-aab4-4c97-9b05-8e7de4e6d604"),
        address = "http://192.168.0.10:8096",
        serverId = "",
    )
