<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

interface FindroidSources {
    val sources: List<FindroidSource>
    val runtimeTicks: Long
    val trickplayInfo: Map<String, FindroidTrickplayInfo>?
}
