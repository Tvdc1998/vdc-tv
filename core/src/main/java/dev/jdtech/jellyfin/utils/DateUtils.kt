<<<<<<< HEAD
package com.vdc.tv.utils

import com.vdc.tv.models.FindroidShow
=======
package dev.jdtech.jellyfin.utils

import dev.jdtech.jellyfin.models.FindroidShow
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

fun getShowDateString(item: FindroidShow): String {
    val dateRange: MutableList<String> = mutableListOf()
    item.productionYear?.let { dateRange.add(it.toString()) }
    when (item.status) {
        "Continuing" -> {
            dateRange.add("Present")
        }

        "Ended" -> {
            item.endDate?.let { dateRange.add(it.year.toString()) }
        }
    }
    if (dateRange.count() > 1 && dateRange[0] == dateRange[1]) return dateRange[0]
    return dateRange.joinToString(separator = " - ")
}
