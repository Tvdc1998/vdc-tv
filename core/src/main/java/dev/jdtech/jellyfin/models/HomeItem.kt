<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import java.util.UUID

sealed class HomeItem {
    data object OfflineCard : HomeItem() {
        override val id: UUID = UUID.fromString("dbfef8a9-7ff0-4c36-9e36-81dfd65fdd46")
    }

    data class Suggestions(override val id: UUID, val items: List<FindroidItem>) : HomeItem()

    data class Section(val homeSection: HomeSection) : HomeItem() {
        override val id = homeSection.id
    }

    data class ViewItem(val view: View) : HomeItem() {
        override val id = view.id
    }

    abstract val id: UUID
}
