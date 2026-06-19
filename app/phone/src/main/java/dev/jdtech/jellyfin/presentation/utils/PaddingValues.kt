<<<<<<< HEAD
package com.vdc.tv.presentation.utils
=======
package dev.jdtech.jellyfin.presentation.utils
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection

@Composable
operator fun PaddingValues.plus(other: PaddingValues): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        start =
            this.calculateStartPadding(layoutDirection) +
                other.calculateStartPadding(layoutDirection),
        top = this.calculateTopPadding() + other.calculateTopPadding(),
        end =
            this.calculateEndPadding(layoutDirection) + other.calculateEndPadding(layoutDirection),
        bottom = this.calculateBottomPadding() + other.calculateBottomPadding(),
    )
}
