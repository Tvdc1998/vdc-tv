<<<<<<< HEAD
package com.vdc.tv.film.presentation.home

import com.vdc.tv.models.HomeItem
import com.vdc.tv.models.Server
import com.vdc.tv.models.User
=======
package dev.jdtech.jellyfin.film.presentation.home

import dev.jdtech.jellyfin.models.HomeItem
import dev.jdtech.jellyfin.models.Server
import dev.jdtech.jellyfin.models.User
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

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
