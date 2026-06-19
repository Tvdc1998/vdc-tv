package com.vdc.tv.film.presentation.person

import com.vdc.tv.models.FindroidItem

sealed interface PersonAction {
    data object NavigateBack : PersonAction

    data object NavigateHome : PersonAction

    data class NavigateToItem(val item: FindroidItem) : PersonAction
}
