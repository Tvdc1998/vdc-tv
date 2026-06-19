package com.vdc.tv.presentation.utils

import androidx.compose.runtime.compositionLocalOf

val LocalOfflineMode = compositionLocalOf<Boolean> { error("Offline mode not defined") }
