<<<<<<< HEAD
package com.vdc.tv.setup.presentation.addresses
=======
package dev.jdtech.jellyfin.setup.presentation.addresses
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import java.util.UUID

sealed interface ServerAddressesAction {
    data class OnServerClick(val addressId: UUID) : ServerAddressesAction

    data class AddAddress(val address: String) : ServerAddressesAction

    data class DeleteAddress(val addressId: UUID) : ServerAddressesAction

    data object OnBackClick : ServerAddressesAction
}
