<<<<<<< HEAD
package com.vdc.tv.setup.presentation.users
=======
package dev.jdtech.jellyfin.setup.presentation.users
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

sealed interface UsersEvent {
    data object NavigateToHome : UsersEvent
}
