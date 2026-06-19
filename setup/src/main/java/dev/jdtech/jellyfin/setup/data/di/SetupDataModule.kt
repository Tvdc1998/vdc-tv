<<<<<<< HEAD
package com.vdc.tv.setup.data.di
=======
package dev.jdtech.jellyfin.setup.data.di
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
<<<<<<< HEAD
import com.vdc.tv.api.JellyfinApi
import com.vdc.tv.database.ServerDatabaseDao
import com.vdc.tv.settings.domain.AppPreferences
import com.vdc.tv.setup.data.SetupRepositoryImpl
import com.vdc.tv.setup.domain.SetupRepository
=======
import dev.jdtech.jellyfin.api.JellyfinApi
import dev.jdtech.jellyfin.database.ServerDatabaseDao
import dev.jdtech.jellyfin.settings.domain.AppPreferences
import dev.jdtech.jellyfin.setup.data.SetupRepositoryImpl
import dev.jdtech.jellyfin.setup.domain.SetupRepository
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SetupDataModule {
    @Singleton
    @Provides
    fun provideSetupRepository(
        jellyfinApi: JellyfinApi,
        serverDatabase: ServerDatabaseDao,
        appPreferences: AppPreferences,
    ): SetupRepository {
        return SetupRepositoryImpl(
            jellyfinApi = jellyfinApi,
            database = serverDatabase,
            appPreferences = appPreferences,
        )
    }
}
