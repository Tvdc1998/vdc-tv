<<<<<<< HEAD
package com.vdc.tv.models

import android.net.Uri
import com.vdc.tv.repository.JellyfinRepository
=======
package dev.jdtech.jellyfin.models

import android.net.Uri
import dev.jdtech.jellyfin.repository.JellyfinRepository
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID
import org.jellyfin.sdk.model.api.BaseItemPerson
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.api.PersonKind

data class FindroidItemPersonImage(val uri: Uri?, val blurHash: String?)

fun BaseItemPerson.toFindroidImage(repository: JellyfinRepository): FindroidItemPersonImage {
    val baseUrl = Uri.parse(repository.getBaseUrl())
    return FindroidItemPersonImage(
        uri =
            primaryImageTag?.let { tag ->
                baseUrl
                    .buildUpon()
                    .appendEncodedPath("items/$id/Images/${ImageType.PRIMARY}")
                    .appendQueryParameter("tag", tag)
                    .build()
            },
        blurHash = imageBlurHashes?.get(ImageType.PRIMARY)?.get(primaryImageTag),
    )
}

data class FindroidItemPerson(
    val id: UUID,
    val name: String,
    val type: PersonKind,
    val role: String,
    val image: FindroidItemPersonImage,
)

fun BaseItemPerson.toFindroidPerson(repository: JellyfinRepository): FindroidItemPerson {
    return FindroidItemPerson(
        id = id,
        name = name.orEmpty(),
        type = type,
        role = role.orEmpty(),
        image = toFindroidImage(repository),
    )
}
