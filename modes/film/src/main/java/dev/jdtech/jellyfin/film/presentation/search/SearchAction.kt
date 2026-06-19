package com.vdc.tv.film.presentation.search

import com.vdc.tv.models.FindroidItem

sealed interface SearchAction {
    data class Search(val query: String) : SearchAction

    data class OnItemClick(val item: FindroidItem) : SearchAction
}
