package dev.jdtech.jellyfin.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jdtech.jellyfin.database.ServerDatabaseDao
import dev.jdtech.jellyfin.models.Server
import dev.jdtech.jellyfin.models.User
import dev.jdtech.jellyfin.settings.domain.AppPreferences
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel
@Inject
constructor(private val appPreferences: AppPreferences, private val database: ServerDatabaseDao) :
    ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    sealed class UiState {
        data class Normal(val server: Server?, val user: User?) : UiState()

        data object Loading : UiState()
    }

    private val preferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, _ -> check() }

    init {
        check()
        appPreferences.sharedPreferences.registerOnSharedPreferenceChangeListener(
            preferenceChangeListener
        )
    }

    override fun onCleared() {
        super.onCleared()
        appPreferences.sharedPreferences.unregisterOnSharedPreferenceChangeListener(
            preferenceChangeListener
        )
    }

    private fun check() {
        viewModelScope.launch {
            val mainState =
                MainState(
                    isLoading = false,
                    isDynamicColors = checkIsDynamicColors(),
                    theme = checkTheme(),
                    hasServers = checkHasServers(),
                    hasCurrentServer = checkHasCurrentServer(),
                    hasCurrentUser = checkHasCurrentUser(),
                    isOfflineMode = checkIsOfflineMode(),
                )
            _state.emit(mainState)
        }
    }

    // Refresh state when coming back from settings
    fun refresh() {
        check()
    }

    fun loadServerAndUser() {
        viewModelScope.launch {
            val serverId = appPreferences.getValue(appPreferences.currentServer)
            serverId?.let { id ->
                database.getServerWithAddressAndUser(id)?.let { data ->
                    _uiState.emit(UiState.Normal(data.server, data.user))
                }
            }
        }
    }

    private fun checkHasServers(): Boolean {
        val nServers = database.getServersCount()
        return nServers > 0
    }

    private fun checkHasCurrentServer(): Boolean {
        return appPreferences.getValue(appPreferences.currentServer)?.let {
            database.get(it) != null
        } == true
    }

    private fun checkHasCurrentUser(): Boolean {
        return appPreferences.getValue(appPreferences.currentServer)?.let {
            database.getServerCurrentUser(it) != null
        } == true
    }

    private fun checkIsDynamicColors(): Boolean {
        return false
    }

    private fun checkTheme(): String {
        return appPreferences.getValue(appPreferences.theme)
    }

    private fun checkIsOfflineMode(): Boolean {
        return appPreferences.getValue(appPreferences.offlineMode)
    }
}

data class MainState(
    val isLoading: Boolean = true,
    val isDynamicColors: Boolean = false,
    val theme: String = "system",
    val hasServers: Boolean = false,
    val hasCurrentServer: Boolean = false,
    val hasCurrentUser: Boolean = false,
    val isOfflineMode: Boolean = false,
)
