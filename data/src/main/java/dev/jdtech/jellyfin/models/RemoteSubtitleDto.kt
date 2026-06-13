package dev.jdtech.jellyfin.models

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
