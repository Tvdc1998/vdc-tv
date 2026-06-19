package com.vdc.tv.film.presentation.search

import com.vdc.tv.models.FindroidItem

data class SearchState(val items: List<FindroidItem> = emptyList(), val loading: Boolean = false)
