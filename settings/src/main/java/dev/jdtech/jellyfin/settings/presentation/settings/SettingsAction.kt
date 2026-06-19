package com.vdc.tv.settings.presentation.settings

import com.vdc.tv.settings.presentation.models.Preference

sealed interface SettingsAction {
    data object OnBackClick : SettingsAction

    data class OnUpdate(val preference: Preference) : SettingsAction
}
