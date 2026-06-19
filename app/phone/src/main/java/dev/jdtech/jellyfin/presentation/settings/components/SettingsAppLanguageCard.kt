<<<<<<< HEAD
package com.vdc.tv.presentation.settings.components
=======
package dev.jdtech.jellyfin.presentation.settings.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
<<<<<<< HEAD
import com.vdc.tv.presentation.theme.spacings
import com.vdc.tv.settings.presentation.models.Preference
=======
import dev.jdtech.jellyfin.presentation.theme.spacings
import dev.jdtech.jellyfin.settings.presentation.models.Preference
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun SettingsAppLanguageCard(preference: Preference, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val currentValue = remember { configuration.locales.get(0).displayName }

    SettingsBaseCard(
        preference = preference,
        onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.startActivity(
                    Intent(
                        Settings.ACTION_APP_LOCALE_SETTINGS,
                        "package:${context.packageName}".toUri(),
                    )
                )
            }
        },
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.padding(MaterialTheme.spacings.medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (preference.iconDrawableId != null) {
                Icon(
                    painter = painterResource(preference.iconDrawableId!!),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacings.default))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(preference.nameStringResource),
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacings.extraSmall))
                Text(text = currentValue, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
