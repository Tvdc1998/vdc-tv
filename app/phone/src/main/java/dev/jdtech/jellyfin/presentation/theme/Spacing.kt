package com.vdc.tv.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.compositionLocalOf
import com.vdc.tv.core.presentation.theme.Spacings

val MaterialTheme.spacings
    get() = Spacings

val LocalSpacings = compositionLocalOf { MaterialTheme.spacings }
