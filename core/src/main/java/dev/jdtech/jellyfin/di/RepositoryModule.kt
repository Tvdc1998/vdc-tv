<<<<<<< HEAD
package com.vdc.tv.di
=======
package dev.jdtech.jellyfin.di
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
<<<<<<< HEAD
import com.vdc.tv.api.JellyfinApi
import com.vdc.tv.api.SubtitleApi
import com.vdc.tv.database.ServerDatabaseDao
import com.vdc.tv.repository.JellyfinRepository
import com.vdc.tv.repository.JellyfinRepositoryImpl
import com.vdc.tv.repository.JellyfinRepositoryOfflineImpl
import com.vdc.tv.settings.domain.AppPreferences
=======
import dev.jdtech.jellyfin.api.JellyfinApi
import dev.jdtech.jellyfin.api.SubtitleApi
import dev.jdtech.jellyfin.database.ServerDatabaseDao
import dev.jdtech.jellyfin.repository.JellyfinRepository
import dev.jdtech.jellyfin.repository.JellyfinRepositoryImpl
import dev.jdtech.jellyfin.repository.JellyfinRepositoryOfflineImpl
import dev.jdtech.jellyfin.settings.domain.AppPreferences
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideJellyfinRepositoryImpl(
        application: Application,
        jellyfinApi: JellyfinApi,
        serverDatabase: ServerDatabaseDao,
        appPreferences: AppPreferences,
        subtitleApi: SubtitleApi,
    ): JellyfinRepositoryImpl {
        println("Creating new jellyfinRepositoryImpl")
        return JellyfinRepositoryImpl(
            application,
            jellyfinApi,
            serverDatabase,
            appPreferences,
            subtitleApi,
        )
    }

    @Singleton
    @Provides
    fun provideJellyfinRepositoryOfflineImpl(
        application: Application,
        jellyfinApi: JellyfinApi,
        serverDatabase: ServerDatabaseDao,
        appPreferences: AppPreferences,
    ): JellyfinRepositoryOfflineImpl {
        println("Creating new jellyfinRepositoryOfflineImpl")
        return JellyfinRepositoryOfflineImpl(
            application,
            jellyfinApi,
            serverDatabase,
            appPreferences,
        )
    }

    @Provides
    fun provideJellyfinRepository(
        jellyfinRepositoryImpl: JellyfinRepositoryImpl,
        jellyfinRepositoryOfflineImpl: JellyfinRepositoryOfflineImpl,
        appPreferences: AppPreferences,
    ): JellyfinRepository {
        println("Creating new JellyfinRepository")
        return when (appPreferences.getValue(appPreferences.offlineMode)) {
            true -> jellyfinRepositoryOfflineImpl
            false -> jellyfinRepositoryImpl
        }
    }
}
