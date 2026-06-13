package dev.jdtech.jellyfin.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.LocalContentColor as LocalContentColorTv
import androidx.tv.material3.MaterialTheme as MaterialThemeTv
import androidx.tv.material3.contentColorFor as contentColorForTv
import dev.jdtech.jellyfin.core.presentation.theme.Spacings

@Composable
fun FindroidTheme(theme: String = "system", content: @Composable BoxScope.() -> Unit) {
    val (colorScheme, colorSchemeTv, background) =
        when (theme) {
            "purple" ->
                Triple(
                    purpleDarkScheme,
                    purpleDarkSchemeTv,
                    listOf(Color(0xFF9C27B0), Color(0xFF7B1FA2)),
                )
            "pink" ->
                Triple(
                    pinkDarkScheme,
                    pinkDarkSchemeTv,
                    listOf(Color(0xFFE977B5), Color(0xFFC2185B)),
                )
            else -> Triple(darkScheme, darkSchemeTv, listOf(Color.Black, Color(0xFF001721)))
        }

    MaterialTheme(colorScheme = colorScheme, typography = Typography, shapes = shapes) {
        MaterialThemeTv(colorScheme = colorSchemeTv, typography = TypographyTv, shapes = shapesTv) {
            CompositionLocalProvider(
                LocalContentColor provides contentColorFor(MaterialTheme.colorScheme.background),
                LocalContentColorTv provides
                    contentColorForTv(MaterialThemeTv.colorScheme.background),
                LocalSpacings provides Spacings,
            ) {
                Box(modifier = Modifier.background(Brush.linearGradient(background))) { content() }
            }
        }
    }
}
