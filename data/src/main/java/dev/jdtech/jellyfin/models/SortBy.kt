<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

enum class SortBy(val sortString: String) {
    NAME("SortName"),
    IMDB_RATING("CommunityRating"),
    PARENTAL_RATING("CriticRating"),
    DATE_ADDED("DateCreated"),
    DATE_PLAYED("DatePlayed"),
    RELEASE_DATE("PremiereDate"),
    SERIES_DATE_PLAYED("SeriesDatePlayed");

    companion object {
        val defaultValue = NAME

        fun fromString(string: String): SortBy {
            return try {
                valueOf(string)
            } catch (_: IllegalArgumentException) {
                defaultValue
            }
        }
    }
}
