<<<<<<< HEAD
package com.vdc.tv.settings.presentation.models
=======
package dev.jdtech.jellyfin.settings.presentation.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.annotation.StringRes

data class PreferenceGroup(
    @param:StringRes val nameStringResource: Int? = null,
    val preferences: List<Preference>,
)
