<<<<<<< HEAD
package com.vdc.tv.film.presentation.search

import com.vdc.tv.models.FindroidItem
=======
package dev.jdtech.jellyfin.film.presentation.search

import dev.jdtech.jellyfin.models.FindroidItem
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class SearchState(val items: List<FindroidItem> = emptyList(), val loading: Boolean = false)
