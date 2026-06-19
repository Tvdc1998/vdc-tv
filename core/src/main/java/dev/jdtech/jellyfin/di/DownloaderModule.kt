package com.vdc.tv.di

import android.app.Application
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.vdc.tv.database.ServerDatabaseDao
import com.vdc.tv.repository.JellyfinRepository
import com.vdc.tv.settings.domain.AppPreferences
import com.vdc.tv.utils.Downloader
import com.vdc.tv.utils.DownloaderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DownloaderModule {
    @Singleton
    @Provides
    fun provideDownloader(
        application: Application,
        serverDatabase: ServerDatabaseDao,
        jellyfinRepository: JellyfinRepository,
        appPreferences: AppPreferences,
        workManager: WorkManager,
    ): Downloader {
        return DownloaderImpl(
            application,
            serverDatabase,
            jellyfinRepository,
            appPreferences,
            workManager,
        )
    }
}
