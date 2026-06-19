<<<<<<< HEAD
package com.vdc.tv.core.presentation.dummy

import com.vdc.tv.models.CollectionType
import com.vdc.tv.models.HomeItem
import com.vdc.tv.models.HomeSection
import com.vdc.tv.models.UiText
import com.vdc.tv.models.View
=======
package dev.jdtech.jellyfin.core.presentation.dummy

import dev.jdtech.jellyfin.models.CollectionType
import dev.jdtech.jellyfin.models.HomeItem
import dev.jdtech.jellyfin.models.HomeSection
import dev.jdtech.jellyfin.models.UiText
import dev.jdtech.jellyfin.models.View
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID

val dummyHomeSuggestions = HomeItem.Suggestions(id = UUID.randomUUID(), items = dummyMovies)

val dummyHomeSection =
    HomeItem.Section(
        HomeSection(
            id = UUID.randomUUID(),
            name = UiText.DynamicString("Continue watching"),
            items = dummyMovies + dummyEpisodes,
        )
    )

val dummyHomeView =
    HomeItem.ViewItem(
        View(
            id = UUID.randomUUID(),
            name = "Movies",
            items = dummyMovies,
            type = CollectionType.Movies,
        )
    )
