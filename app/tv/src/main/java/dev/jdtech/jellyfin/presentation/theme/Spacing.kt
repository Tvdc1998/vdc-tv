<<<<<<< HEAD
package com.vdc.tv.presentation.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.tv.material3.MaterialTheme
import com.vdc.tv.core.presentation.theme.Spacings
=======
package dev.jdtech.jellyfin.presentation.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.tv.material3.MaterialTheme
import dev.jdtech.jellyfin.core.presentation.theme.Spacings
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

val MaterialTheme.spacings
    get() = Spacings

val LocalSpacings = compositionLocalOf { MaterialTheme.spacings }
