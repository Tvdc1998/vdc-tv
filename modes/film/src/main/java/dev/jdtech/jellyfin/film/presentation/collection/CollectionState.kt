<<<<<<< HEAD
package com.vdc.tv.film.presentation.collection

import com.vdc.tv.models.CollectionSection
=======
package dev.jdtech.jellyfin.film.presentation.collection

import dev.jdtech.jellyfin.models.CollectionSection
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class CollectionState(
    val sections: List<CollectionSection> = emptyList(),
    val isLoading: Boolean = false,
    val error: Exception? = null,
)
