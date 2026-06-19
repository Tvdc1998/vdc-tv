<<<<<<< HEAD
package com.vdc.tv
=======
package dev.jdtech.jellyfin
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
<<<<<<< HEAD
import com.vdc.tv.presentation.theme.FindroidTheme
import com.vdc.tv.viewmodels.MainViewModel
=======
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
import dev.jdtech.jellyfin.viewmodels.MainViewModel
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()

            FindroidTheme(theme = state.theme) {
                val navController = rememberNavController()
                if (!state.isLoading) {
                    NavigationRoot(
                        navController = navController,
                        hasServers = state.hasServers,
                        hasCurrentServer = state.hasCurrentServer,
                        hasCurrentUser = state.hasCurrentUser,
                    )
                }
            }
        }
    }
}
