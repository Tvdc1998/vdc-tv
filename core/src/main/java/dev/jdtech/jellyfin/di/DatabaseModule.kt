package com.vdc.tv.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.vdc.tv.database.MIGRATION_6_7
import com.vdc.tv.database.ServerDatabase
import com.vdc.tv.database.ServerDatabaseDao
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
