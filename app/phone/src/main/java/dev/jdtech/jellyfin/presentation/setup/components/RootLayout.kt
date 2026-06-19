<<<<<<< HEAD
package com.vdc.tv.presentation.setup.components
=======
package dev.jdtech.jellyfin.presentation.setup.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import com.vdc.tv.presentation.utils.plus
import com.vdc.tv.presentation.utils.rememberSafePadding
=======
import dev.jdtech.jellyfin.presentation.utils.plus
import dev.jdtech.jellyfin.presentation.utils.rememberSafePadding
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun RootLayout(padding: PaddingValues = PaddingValues(), content: @Composable BoxScope.() -> Unit) {
    val safePadding = rememberSafePadding()

    val safePaddingValues =
        PaddingValues(
            start = safePadding.start,
            top = safePadding.top,
            end = safePadding.end,
            bottom = safePadding.bottom,
        )

    Box(modifier = Modifier.fillMaxSize().padding(safePaddingValues + padding), content = content)
}
