<<<<<<< HEAD
package com.vdc.tv.setup.presentation.welcome
=======
package dev.jdtech.jellyfin.setup.presentation.welcome
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface WelcomeAction {
    data object OnContinueClick : WelcomeAction

    data object OnLearnMoreClick : WelcomeAction
}
