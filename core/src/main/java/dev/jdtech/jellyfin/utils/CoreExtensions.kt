<<<<<<< HEAD
package com.vdc.tv.utils
=======
package dev.jdtech.jellyfin.utils
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.util.Base64
<<<<<<< HEAD
import com.vdc.tv.models.CollectionType
import com.vdc.tv.models.FindroidItem
import com.vdc.tv.models.View
=======
import dev.jdtech.jellyfin.models.CollectionType
import dev.jdtech.jellyfin.models.FindroidItem
import dev.jdtech.jellyfin.models.View
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.nio.charset.StandardCharsets
import java.text.DateFormat
import java.time.ZoneOffset
import java.util.Date
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.api.BaseItemDto

fun BaseItemDto.toView(items: List<FindroidItem>): View {
    return View(
        id = id,
        name = name ?: "",
        items = items,
        type = CollectionType.fromString(collectionType?.serialName),
    )
}

fun Resources.dip(px: Int) = (px * displayMetrics.density).toInt()

fun Activity.restart() {
    val intent = Intent(this, this::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
}

fun String.base64ToByteArray(): ByteArray {
    return Base64.decode(toByteArray(StandardCharsets.UTF_8), Base64.URL_SAFE or Base64.NO_WRAP)
}

fun ByteArray.toBase64Str(): String {
    return Base64.encodeToString(this, Base64.URL_SAFE or Base64.NO_WRAP)
}

fun DateTime.format(): String {
    val instant = this.toInstant(ZoneOffset.UTC)
    val date = Date.from(instant)
    return DateFormat.getDateInstance(DateFormat.SHORT).format(date)
}
