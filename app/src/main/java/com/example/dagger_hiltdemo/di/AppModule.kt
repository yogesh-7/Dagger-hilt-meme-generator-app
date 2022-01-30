package com.example.dagger_hiltdemo.di

import com.example.dagger_hiltdemo.data.source.MemeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUrl() = "https://meme-api.herokuapp.com/"

    @Provides
    @Singleton
    fun provideAPIServices(url: String): MemeService =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
            .create(MemeService::class.java)

}