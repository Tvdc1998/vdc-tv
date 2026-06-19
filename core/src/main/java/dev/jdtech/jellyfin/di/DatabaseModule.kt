<<<<<<< HEAD
package com.vdc.tv.di
=======
package dev.jdtech.jellyfin.di
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
<<<<<<< HEAD
import com.vdc.tv.database.MIGRATION_6_7
import com.vdc.tv.database.ServerDatabase
import com.vdc.tv.database.ServerDatabaseDao
=======
import dev.jdtech.jellyfin.database.MIGRATION_6_7
import dev.jdtech.jellyfin.database.ServerDatabase
import dev.jdtech.jellyfin.database.ServerDatabaseDao
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideServerDatabaseDao(@ApplicationContext app: Context): ServerDatabaseDao {
        return Room.databaseBuilder(app.applicationContext, ServerDatabase::class.java, "servers")
            .addMigrations(MIGRATION_6_7)
            .fallbackToDestructiveMigration(dropAllTables = true)
            .allowMainThreadQueries()
            .build()
            .getServerDatabaseDao()
    }
}
