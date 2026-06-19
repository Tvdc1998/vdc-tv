<<<<<<< HEAD
package com.vdc.tv.settings.domain.models
=======
package dev.jdtech.jellyfin.settings.domain.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class Preference<out T>(val backendName: String, val defaultValue: T)
