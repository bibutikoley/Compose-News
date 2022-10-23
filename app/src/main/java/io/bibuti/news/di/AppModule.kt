package io.bibuti.news.di

import android.app.Application
import android.content.res.Resources
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.bibuti.news.App
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApplication(application: Application): App = application as App

    @Provides
    @Singleton
    fun providesResources(application: Application): Resources = application.resources
}
