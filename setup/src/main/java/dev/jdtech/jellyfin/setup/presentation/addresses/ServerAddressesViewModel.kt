<<<<<<< HEAD
package com.vdc.tv.setup.presentation.addresses
=======
package dev.jdtech.jellyfin.setup.presentation.addresses
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
<<<<<<< HEAD
import com.vdc.tv.api.JellyfinApi
import com.vdc.tv.database.ServerDatabaseDao
import com.vdc.tv.models.ServerAddress
=======
import dev.jdtech.jellyfin.api.JellyfinApi
import dev.jdtech.jellyfin.database.ServerDatabaseDao
import dev.jdtech.jellyfin.models.ServerAddress
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class ServerAddressesViewModel
@Inject
constructor(val application: Application, private val database: ServerDatabaseDao) : ViewModel() {
    private val _state = MutableStateFlow(ServerAddressesState())
    val state = _state.asStateFlow()

    private var currentServerId: String = ""

    fun loadAddresses(serverId: String) {
        currentServerId = serverId
        viewModelScope.launch {
            try {
                val serverWithAddresses = database.getServerWithAddresses(serverId)
                _state.emit(ServerAddressesState(addresses = serverWithAddresses.addresses))
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun deleteAddress(addressId: UUID) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentAddress = database.getServerCurrentAddress(currentServerId)
            if (addressId == currentAddress?.id) {
                Timber.e("You cannot delete the current address")
                return@launch
            }
            database.deleteServerAddress(addressId)
            loadAddresses(currentServerId)
        }
    }

    fun addAddress(address: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val jellyfinApi = JellyfinApi(application.applicationContext)
                jellyfinApi.api.update(baseUrl = address)
                val systemInfo by jellyfinApi.systemApi.getPublicSystemInfo()
                if (systemInfo.id != currentServerId) {
                    return@launch
                }
                val serverAddress = ServerAddress(UUID.randomUUID(), currentServerId, address)
                database.insertServerAddress(serverAddress)
                loadAddresses(currentServerId)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun onAction(action: ServerAddressesAction) {
        when (action) {
            is ServerAddressesAction.AddAddress -> {
                addAddress(action.address)
            }
            is ServerAddressesAction.DeleteAddress -> {
                deleteAddress(action.addressId)
            }
            else -> Unit
        }
    }
}
