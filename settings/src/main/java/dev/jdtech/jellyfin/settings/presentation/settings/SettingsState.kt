<<<<<<< HEAD
package com.vdc.tv.settings.presentation.settings

import com.vdc.tv.settings.presentation.models.PreferenceGroup
=======
package dev.jdtech.jellyfin.settings.presentation.settings

import dev.jdtech.jellyfin.settings.presentation.models.PreferenceGroup
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class SettingsState(
    val isLoading: Boolean = false,
    val preferenceGroups: List<PreferenceGroup> = emptyList(),
)
