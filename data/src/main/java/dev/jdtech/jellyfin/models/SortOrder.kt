<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

enum class SortOrder(val sortString: String) {
    ASCENDING("Ascending"),
    DESCENDING("Descending");

    companion object {
        val defaultValue = ASCENDING

        fun fromString(string: String): SortOrder {
            return try {
                valueOf(string)
            } catch (_: IllegalArgumentException) {
                defaultValue
            }
        }
    }
}
