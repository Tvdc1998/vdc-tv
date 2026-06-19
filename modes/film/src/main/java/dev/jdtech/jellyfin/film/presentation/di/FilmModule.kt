<<<<<<< HEAD
package com.vdc.tv.film.presentation.di
=======
package dev.jdtech.jellyfin.film.presentation.di
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
<<<<<<< HEAD
import com.vdc.tv.film.domain.VideoMetadataParser
=======
import dev.jdtech.jellyfin.film.domain.VideoMetadataParser
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FilmModule {
    @Singleton
    @Provides
    fun provideVideoMetadataParser(): VideoMetadataParser {
        return VideoMetadataParser
    }
}
