<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class DynamicString(val value: String) : UiText()

    class StringResource(@param:StringRes val resId: Int, vararg val args: Any) : UiText()

    fun asString(resources: Resources): String {
        return when (this) {
            is DynamicString -> return value
            is StringResource -> resources.getString(resId, *args)
        }
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args)
        }
    }
}
