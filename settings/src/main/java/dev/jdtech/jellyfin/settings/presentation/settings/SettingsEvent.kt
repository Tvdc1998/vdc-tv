<<<<<<< HEAD
package com.vdc.tv.settings.presentation.settings
=======
package dev.jdtech.jellyfin.settings.presentation.settings
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.content.Intent

sealed interface SettingsEvent {
    data object NavigateToUsers : SettingsEvent

    data object NavigateToServers : SettingsEvent

    data object NavigateToAbout : SettingsEvent

    data class NavigateToSettings(val indexes: IntArray) : SettingsEvent

    data class UpdateTheme(val theme: String) : SettingsEvent

    data class LaunchIntent(val intent: Intent) : SettingsEvent

    data object RestartActivity : SettingsEvent
}
