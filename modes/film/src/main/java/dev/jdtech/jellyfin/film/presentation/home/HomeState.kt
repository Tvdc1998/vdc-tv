package com.vdc.tv.film.presentation.home

import com.vdc.tv.models.HomeItem
import com.vdc.tv.models.Server
import com.vdc.tv.models.User

data class HomeState(
    val server: Server? = null,
    val user: User? = null,
    val suggestionsSection: HomeItem.Suggestions? = null,
    val resumeSection: HomeItem.Section? = null,
    val nextUpSection: HomeItem.Section? = null,
    val genreSections: List<HomeItem.Section> = emptyList(),
    val views: List<HomeItem.ViewItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: Exception? = null,
)
