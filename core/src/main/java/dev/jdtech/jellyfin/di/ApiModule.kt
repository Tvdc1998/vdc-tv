<<<<<<< HEAD
package com.vdc.tv.di
=======
package dev.jdtech.jellyfin.di
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
<<<<<<< HEAD
import com.vdc.tv.api.JellyfinApi
import com.vdc.tv.api.SubtitleApi
import com.vdc.tv.database.ServerDatabaseDao
import com.vdc.tv.settings.domain.AppPreferences
=======
import dev.jdtech.jellyfin.api.JellyfinApi
import dev.jdtech.jellyfin.api.SubtitleApi
import dev.jdtech.jellyfin.database.ServerDatabaseDao
import dev.jdtech.jellyfin.settings.domain.AppPreferences
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideJellyfinApi(
        @ApplicationContext application: Context,
        appPreferences: AppPreferences,
        database: ServerDatabaseDao,
    ): JellyfinApi {
        val jellyfinApi =
            JellyfinApi.getInstance(
                context = application,
                requestTimeout = appPreferences.getValue(appPreferences.requestTimeout),
                connectTimeout = appPreferences.getValue(appPreferences.connectTimeout),
                socketTimeout = appPreferences.getValue(appPreferences.socketTimeout),
            )

        val serverId = appPreferences.getValue(appPreferences.currentServer) ?: return jellyfinApi

        val serverWithAddressAndUser =
            database.getServerWithAddressAndUser(serverId) ?: return jellyfinApi
        val serverAddress = serverWithAddressAndUser.address ?: return jellyfinApi
        val user = serverWithAddressAndUser.user

        jellyfinApi.apply {
            api.update(baseUrl = serverAddress.address, accessToken = user?.accessToken)
            userId = user?.id
            accessToken = user?.accessToken
        }

        return jellyfinApi
    }

    @Singleton
    @Provides
    fun provideSubtitleApi(jellyfinApi: JellyfinApi): SubtitleApi {
        return SubtitleApi(jellyfinApi)
    }
}
