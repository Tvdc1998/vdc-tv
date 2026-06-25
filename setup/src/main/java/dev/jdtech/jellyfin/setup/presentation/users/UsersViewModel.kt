package com.vdc.tv.setup.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdc.tv.setup.domain.SetupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: SetupRepository) : ViewModel() {
    private val _state = MutableStateFlow(UsersState())
    val state: StateFlow<UsersState> = _state.asStateFlow()

    private val eventsChannel = Channel<UsersEvent>()
    val events: Flow<UsersEvent> = eventsChannel.receiveAsFlow()

    fun loadUsers() {
        viewModelScope.launch {
            try {
                val server = repository.getCurrentServer() ?: return@launch
                val users = repository.getUsers(server.id)
                val publicUsers = repository.getPublicUsers(server.id)
                
                // Filter out public users that are already in the local database
                val localUserIds = users.map { it.id }.toSet()
                val uniquePublicUsers = publicUsers.filter { it.id !in localUserIds }

                _state.update {
                    it.copy(
                        users = users, 
                        publicUsers = uniquePublicUsers, 
                        serverName = server.name
                    )
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun deleteUser(userId: UUID) {
        viewModelScope.launch {
            try {
                repository.deleteUser(userId)
                loadUsers()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun loginAsUser(userId: UUID) {
        viewModelScope.launch {
            try {
                repository.setCurrentUser(userId)
                eventsChannel.send(UsersEvent.NavigateToHome)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun loginWithPin(username: String, pin: String) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(error = null) }
                repository.login(username, pin)
                eventsChannel.send(UsersEvent.NavigateToHome)
            } catch (e: Exception) {
                Timber.e(e)
                _state.update { it.copy(error = "Invalid PIN") }
            }
        }
    }

    fun onAction(action: UsersAction) {
        when (action) {
            is UsersAction.OnUserClick -> {
                if (action.user.hasPassword) {
                    _state.update { it.copy(selectedUser = action.user, showPinDialog = true) }
                } else {
                    loginAsUser(action.user.id)
                }
            }
            is UsersAction.OnPublicUserClick -> {
                if (action.user.hasPassword) {
                    _state.update { it.copy(selectedUser = action.user, showPinDialog = true) }
                } else {
                    loginAsUser(action.user.id)
                }
            }
            is UsersAction.OnPinSubmit -> {
                val user = _state.value.selectedUser ?: return
                loginWithPin(user.name, action.pin)
            }
            is UsersAction.OnDismissPinDialog -> {
                _state.update { it.copy(showPinDialog = false, selectedUser = null, error = null) }
            }
            is UsersAction.OnDeleteUser -> {
                deleteUser(action.userId)
            }
            else -> Unit
        }
    }
}
