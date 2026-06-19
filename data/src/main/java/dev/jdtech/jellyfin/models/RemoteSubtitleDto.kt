<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSubtitleDto(
    @SerialName("Id")
    val id: String,
    @SerialName("Language")
    val language: String? = null,
    @SerialName("ThreeLetterISOLanguageName")
    val isoLanguage: String? = null,
    @SerialName("ProviderName")
    val providerName: String? = null,
    @SerialName("Name")
    val name: String? = null,
    @SerialName("Format")
    val format: String? = null,
)
