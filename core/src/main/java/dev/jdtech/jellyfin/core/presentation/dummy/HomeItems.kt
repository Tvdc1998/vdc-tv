package com.vdc.tv.core.presentation.dummy

import com.vdc.tv.models.CollectionType
import com.vdc.tv.models.HomeItem
import com.vdc.tv.models.HomeSection
import com.vdc.tv.models.UiText
import com.vdc.tv.models.View
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
