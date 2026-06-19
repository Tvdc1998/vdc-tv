<<<<<<< HEAD
package com.vdc.tv.settings.presentation.settings

import com.vdc.tv.settings.presentation.models.Preference
=======
package dev.jdtech.jellyfin.settings.presentation.settings

import dev.jdtech.jellyfin.settings.presentation.models.Preference
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface SettingsAction {
    data object OnBackClick : SettingsAction

    data class OnUpdate(val preference: Preference) : SettingsAction
}
