package com.vdc.tv.presentation.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.tv.material3.MaterialTheme
import com.vdc.tv.core.presentation.theme.Spacings

val MaterialTheme.spacings
    get() = Spacings

val LocalSpacings = compositionLocalOf { MaterialTheme.spacings }
