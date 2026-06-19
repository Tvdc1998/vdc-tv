package com.vdc.tv.core.presentation.dummy

import com.vdc.tv.models.CollectionType
import com.vdc.tv.models.FindroidCollection
import com.vdc.tv.models.FindroidImages
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
