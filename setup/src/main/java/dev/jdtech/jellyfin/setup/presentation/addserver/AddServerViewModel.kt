<<<<<<< HEAD
package com.vdc.tv.setup.presentation.addserver
=======
package dev.jdtech.jellyfin.setup.presentation.addserver
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
<<<<<<< HEAD
import com.vdc.tv.core.R as CoreR
import com.vdc.tv.models.DiscoveredServer
import com.vdc.tv.models.ExceptionUiText
import com.vdc.tv.models.ExceptionUiTexts
import com.vdc.tv.models.UiText
import com.vdc.tv.settings.domain.AppPreferences
import com.vdc.tv.setup.domain.SetupRepository
=======
import dev.jdtech.jellyfin.core.R as CoreR
import dev.jdtech.jellyfin.models.DiscoveredServer
import dev.jdtech.jellyfin.models.ExceptionUiText
import dev.jdtech.jellyfin.models.ExceptionUiTexts
import dev.jdtech.jellyfin.models.UiText
import dev.jdtech.jellyfin.settings.domain.AppPreferences
import dev.jdtech.jellyfin.setup.domain.SetupRepository
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddServerViewModel
@Inject
constructor(private val repository: SetupRepository, private val appPreferences: AppPreferences) :
    ViewModel() {
    private val _state = MutableStateFlow(AddServerState())
    val state = _state.asStateFlow()

    private val eventsChannel = Channel<AddServerEvent>()
    val events = eventsChannel.receiveAsFlow()

    fun discoverServers() {
        viewModelScope.launch {
            val discoveredServers = mutableListOf<DiscoveredServer>()
            val serversDiscovery = repository.discoverServers()
            serversDiscovery.collect { serverDiscoveryInfo ->
                discoveredServers.add(
                    DiscoveredServer(
                        serverDiscoveryInfo.id,
                        serverDiscoveryInfo.name,
                        serverDiscoveryInfo.address,
                    )
                )
                _state.emit(_state.value.copy(discoveredServers = discoveredServers))
            }
        }
    }

    private fun connectToServer(address: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(isLoading = true, error = null))

            try {
                val server = repository.addServer(address)
                appPreferences.setValue(appPreferences.currentServer, server.id)
                repository.setCurrentServer(server.id)
                _state.emit(_state.value.copy(isLoading = false, error = null))
                eventsChannel.send(AddServerEvent.Success)
            } catch (_: CancellationException) {} catch (e: ExceptionUiText) {
                _state.emit(_state.value.copy(isLoading = false, error = listOf(e.uiText)))
            } catch (e: ExceptionUiTexts) {
                _state.emit(_state.value.copy(isLoading = false, error = e.uiTexts))
            } catch (e: Exception) {
                _state.emit(
                    _state.value.copy(
                        isLoading = false,
                        error =
                            listOf(
                                if (e.message != null) UiText.DynamicString(e.message!!)
                                else UiText.StringResource(CoreR.string.unknown_error)
                            ),
                    )
                )
            }
        }
    }

    fun onAction(action: AddServerAction) {
        when (action) {
            is AddServerAction.OnConnectClick -> {
                connectToServer(address = action.address)
            }
            else -> Unit
        }
    }
}
