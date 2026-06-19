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
import com.vdc.tv.BaseApplication
=======
import dev.jdtech.jellyfin.BaseApplication
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }
}
