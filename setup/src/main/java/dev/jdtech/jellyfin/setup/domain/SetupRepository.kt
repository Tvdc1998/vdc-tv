<<<<<<< HEAD
package com.vdc.tv.setup.domain

import com.vdc.tv.models.Server
import com.vdc.tv.models.ServerWithAddresses
import com.vdc.tv.models.User
=======
package dev.jdtech.jellyfin.setup.domain

import dev.jdtech.jellyfin.models.Server
import dev.jdtech.jellyfin.models.ServerWithAddresses
import dev.jdtech.jellyfin.models.User
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import org.jellyfin.sdk.model.api.QuickConnectResult
import org.jellyfin.sdk.model.api.ServerDiscoveryInfo

interface SetupRepository {
    fun discoverServers(): Flow<ServerDiscoveryInfo>

    suspend fun getServers(): List<ServerWithAddresses>

    suspend fun getCurrentServer(): Server?

    suspend fun deleteServer(serverId: String)

    suspend fun getIsQuickConnectEnabled(): Boolean

    suspend fun initiateQuickConnect(): QuickConnectResult

    suspend fun getQuickConnectState(secret: String): QuickConnectResult

    suspend fun setCurrentServer(serverId: String)

    suspend fun addServer(address: String): Server

    suspend fun loadDisclaimer(): String?

    suspend fun login(username: String, password: String)

    suspend fun loginWithSecret(secret: String)

    suspend fun getUsers(serverId: String): List<User>

    suspend fun getPublicUsers(serverId: String): List<User>

    suspend fun getCurrentUser(): User?

    suspend fun deleteUser(userId: UUID)

    suspend fun setCurrentUser(userId: UUID)

    suspend fun setCurrentAddress(addressId: UUID)
}
