<<<<<<< HEAD
package com.vdc.tv.presentation.settings.components
=======
package dev.jdtech.jellyfin.presentation.settings.components
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
<<<<<<< HEAD
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.presentation.theme.spacings
import com.vdc.tv.settings.R as SettingsR
import com.vdc.tv.settings.presentation.models.PreferenceCategory
=======
import dev.jdtech.jellyfin.core.R as CoreR
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
import dev.jdtech.jellyfin.presentation.theme.spacings
import dev.jdtech.jellyfin.settings.R as SettingsR
import dev.jdtech.jellyfin.settings.presentation.models.PreferenceCategory
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun SettingsCategoryCard(preference: PreferenceCategory, modifier: Modifier = Modifier) {
    SettingsBaseCard(
        preference = preference,
        onClick = { preference.onClick(preference) },
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
            } else {
                Spacer(modifier = Modifier.size(MaterialTheme.spacings.default))
            }

            Spacer(modifier = Modifier.width(MaterialTheme.spacings.default))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(preference.nameStringResource),
                    style = MaterialTheme.typography.titleMedium,
                )
                preference.descriptionStringRes?.let {
                    Spacer(modifier = Modifier.height(MaterialTheme.spacings.extraSmall))
                    Text(
                        text = stringResource(id = it),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SettingsCategoryCardPreview() {
    FindroidTheme {
        SettingsCategoryCard(
            preference =
                PreferenceCategory(
                    nameStringResource = SettingsR.string.settings_category_player,
                    iconDrawableId = CoreR.drawable.ic_play,
                )
        )
    }
}
