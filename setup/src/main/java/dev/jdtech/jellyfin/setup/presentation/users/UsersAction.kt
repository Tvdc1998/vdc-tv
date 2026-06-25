package com.vdc.tv.setup.presentation.users

import com.vdc.tv.models.User
import java.util.UUID

sealed interface UsersAction {
    data class OnUserClick(val user: User) : UsersAction

    data class OnPublicUserClick(val user: User) : UsersAction

    data class OnPinSubmit(val pin: String) : UsersAction

    data object OnDismissPinDialog : UsersAction

    data class OnDeleteUser(val userId: UUID) : UsersAction

    data object OnChangeServerClick : UsersAction

    data object OnAddClick : UsersAction

    data object OnBackClick : UsersAction
}
