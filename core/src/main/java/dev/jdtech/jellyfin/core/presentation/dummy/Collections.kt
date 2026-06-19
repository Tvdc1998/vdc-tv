<<<<<<< HEAD
package com.vdc.tv.core.presentation.dummy

import com.vdc.tv.models.CollectionType
import com.vdc.tv.models.FindroidCollection
import com.vdc.tv.models.FindroidImages
=======
package dev.jdtech.jellyfin.core.presentation.dummy

import dev.jdtech.jellyfin.models.CollectionType
import dev.jdtech.jellyfin.models.FindroidCollection
import dev.jdtech.jellyfin.models.FindroidImages
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID

private val dummyMoviesCollection =
    FindroidCollection(
        id = UUID.randomUUID(),
        name = "Movies",
        type = CollectionType.Movies,
        images = FindroidImages(),
    )

private val dummyShowsCollection =
    FindroidCollection(
        id = UUID.randomUUID(),
        name = "Shows",
        type = CollectionType.TvShows,
        images = FindroidImages(),
    )

val dummyCollections = listOf(dummyMoviesCollection, dummyShowsCollection)
