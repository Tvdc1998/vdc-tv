<<<<<<< HEAD
package com.vdc.tv.settings.presentation.models

import com.vdc.tv.settings.domain.models.Preference as PreferenceBackend
import com.vdc.tv.settings.presentation.enums.DeviceType
=======
package dev.jdtech.jellyfin.settings.presentation.models

import dev.jdtech.jellyfin.settings.domain.models.Preference as PreferenceBackend
import dev.jdtech.jellyfin.settings.presentation.enums.DeviceType
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

interface Preference {
    val nameStringResource: Int
    val descriptionStringRes: Int?
    val iconDrawableId: Int?
    val enabled: Boolean
    val dependencies: List<PreferenceBackend<Boolean>>
    val supportedDeviceTypes: List<DeviceType>
}
