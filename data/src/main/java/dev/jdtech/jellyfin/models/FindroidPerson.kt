<<<<<<< HEAD
package com.vdc.tv.models

import com.vdc.tv.repository.JellyfinRepository
=======
package dev.jdtech.jellyfin.models

import dev.jdtech.jellyfin.repository.JellyfinRepository
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID
import org.jellyfin.sdk.model.api.BaseItemDto

data class FindroidPerson(
    val id: UUID,
    val name: String,
    val overview: String,
    val images: FindroidImages,
)

fun BaseItemDto.toFindroidPerson(repository: JellyfinRepository): FindroidPerson {
    return FindroidPerson(
        id = id,
        name = name.orEmpty(),
        overview = overview.orEmpty(),
        images = toFindroidImages(repository),
    )
}
