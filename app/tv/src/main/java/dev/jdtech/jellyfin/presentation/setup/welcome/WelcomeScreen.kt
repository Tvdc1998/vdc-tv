<<<<<<< HEAD
package com.vdc.tv.presentation.setup.welcome
=======
package dev.jdtech.jellyfin.presentation.setup.welcome
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text
<<<<<<< HEAD
import com.vdc.tv.R
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.presentation.theme.spacings
import com.vdc.tv.setup.presentation.welcome.WelcomeAction
=======
import dev.jdtech.jellyfin.R
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
import dev.jdtech.jellyfin.presentation.theme.spacings
import dev.jdtech.jellyfin.setup.presentation.welcome.WelcomeAction
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@Composable
fun WelcomeScreen(onContinueClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current

    WelcomeScreenLayout(
        onAction = { action ->
            when (action) {
                is WelcomeAction.OnContinueClick -> onContinueClick()
                is WelcomeAction.OnLearnMoreClick -> {
                    uriHandler.openUri("https://jellyfin.org/")
                }
            }
        }
    )
}

@Composable
private fun WelcomeScreenLayout(onAction: (WelcomeAction) -> Unit) {
    val focusRequester = remember { FocusRequester() }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_banner),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.width(250.dp),
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacings.medium))
            Text(
<<<<<<< HEAD
                text = stringResource(com.vdc.tv.setup.R.string.welcome),
=======
                text = stringResource(dev.jdtech.jellyfin.setup.R.string.welcome),
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
                style = MaterialTheme.typography.displayMedium,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacings.default))
            Text(
<<<<<<< HEAD
                text = stringResource(com.vdc.tv.setup.R.string.welcome_text),
=======
                text = stringResource(dev.jdtech.jellyfin.setup.R.string.welcome_text),
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacings.large))
            OutlinedButton(onClick = { onAction(WelcomeAction.OnLearnMoreClick) }) {
                Text(
<<<<<<< HEAD
                    text = stringResource(com.vdc.tv.setup.R.string.welcome_btn_learn_more)
=======
                    text = stringResource(dev.jdtech.jellyfin.setup.R.string.welcome_btn_learn_more)
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacings.medium))
            Button(
                onClick = { onAction(WelcomeAction.OnContinueClick) },
                modifier = Modifier.focusRequester(focusRequester),
            ) {
<<<<<<< HEAD
                Text(text = stringResource(com.vdc.tv.setup.R.string.welcome_btn_continue))
=======
                Text(text = stringResource(dev.jdtech.jellyfin.setup.R.string.welcome_btn_continue))
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
            }
        }
    }

    LaunchedEffect(true) { focusRequester.requestFocus() }
}

@Preview(device = "id:tv_1080p")
@Composable
private fun WelcomeScreenLayoutPreview() {
    FindroidTheme { WelcomeScreenLayout(onAction = {}) }
}
