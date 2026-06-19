<<<<<<< HEAD
package com.vdc.tv.di
=======
package dev.jdtech.jellyfin.di
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
<<<<<<< HEAD
import com.vdc.tv.settings.domain.AppPreferences
=======
import dev.jdtech.jellyfin.settings.domain.AppPreferences
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppPreferencesModule {
    @Singleton
    @Provides
    fun provideAppPreferences(sp: SharedPreferences): AppPreferences {
        return AppPreferences(sp)
    }
}
