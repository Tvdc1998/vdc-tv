package com.vdc.tv.setup.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.vdc.tv.api.JellyfinApi
import com.vdc.tv.database.ServerDatabaseDao
import com.vdc.tv.settings.domain.AppPreferences
import com.vdc.tv.setup.data.SetupRepositoryImpl
import com.vdc.tv.setup.domain.SetupRepository
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
