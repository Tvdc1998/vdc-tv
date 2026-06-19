<<<<<<< HEAD
package com.vdc.tv.settings.domain
=======
package dev.jdtech.jellyfin.settings.domain
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

object Constants {
    // Player - Media Segments
    object PlayerMediaSegmentsAutoSkip {
        const val ALWAYS = "always"
        const val PIP = "pip"
    }

    // Network
    const val NETWORK_DEFAULT_REQUEST_TIMEOUT = 30_000L
    const val NETWORK_DEFAULT_CONNECT_TIMEOUT = 6_000L
    const val NETWORK_DEFAULT_SOCKET_TIMEOUT = 10_000L
}
