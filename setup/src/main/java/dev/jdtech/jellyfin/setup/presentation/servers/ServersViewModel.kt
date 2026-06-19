<<<<<<< HEAD
package com.vdc.tv.setup.presentation.servers
=======
package dev.jdtech.jellyfin.setup.presentation.servers
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
<<<<<<< HEAD
import com.vdc.tv.settings.domain.AppPreferences
import com.vdc.tv.setup.domain.SetupRepository
=======
import dev.jdtech.jellyfin.settings.domain.AppPreferences
import dev.jdtech.jellyfin.setup.domain.SetupRepository
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ServersViewModel
@Inject
constructor(private val repository: SetupRepository, private val appPreferences: AppPreferences) :
    ViewModel() {
    private val _state = MutableStateFlow(ServersState())
    val state = _state.asStateFlow()

    private val eventsChannel = Channel<ServersEvent>()
    val events = eventsChannel.receiveAsFlow()

    fun loadServers() {
        viewModelScope.launch {
            val servers = repository.getServers()
            _state.emit(ServersState(servers = servers))
        }
    }

    private fun setCurrentServer(serverId: String) {
        viewModelScope.launch {
            repository.setCurrentServer(serverId)

            appPreferences.setValue(appPreferences.currentServer, serverId)

            eventsChannel.send(ServersEvent.ServerChanged)
        }
    }

    private fun setCurrentAddress(addressId: UUID) {
        viewModelScope.launch {
            repository.setCurrentAddress(addressId)

            eventsChannel.send(ServersEvent.AddressChanged)
        }
    }

    private fun deleteServer(serverId: String) {
        viewModelScope.launch {
            repository.deleteServer(serverId)
            loadServers()
        }
    }

    fun onAction(action: ServersAction) {
        when (action) {
            is ServersAction.OnServerClick -> {
                setCurrentServer(action.serverId)
            }
            is ServersAction.OnAddressClick -> {
                setCurrentAddress(action.addressId)
            }
            is ServersAction.DeleteServer -> {
                deleteServer(action.serverId)
            }
            else -> Unit
        }
    }
}
