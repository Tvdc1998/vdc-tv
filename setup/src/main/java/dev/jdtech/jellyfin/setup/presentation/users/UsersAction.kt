<<<<<<< HEAD
package com.vdc.tv.setup.presentation.users
=======
package dev.jdtech.jellyfin.setup.presentation.users
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import java.util.UUID

sealed interface UsersAction {
    data class OnUserClick(val userId: UUID) : UsersAction

    data class OnPublicUserClick(val username: String) : UsersAction

    data class OnDeleteUser(val userId: UUID) : UsersAction

    data object OnChangeServerClick : UsersAction

    data object OnAddClick : UsersAction

    data object OnBackClick : UsersAction
}
