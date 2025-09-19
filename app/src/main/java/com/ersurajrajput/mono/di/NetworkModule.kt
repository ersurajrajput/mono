package com.ersurajrajput.mono.di

import com.ersurajrajput.mono.api.TweetsyAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val BaseUrl = "https://raw.githubusercontent.com/ersurajrajput/mono/refs/heads/main/app/src/main/res/raw/"
    @Singleton
    @Provides
    fun  provideRetroFit(): Retrofit{
        return Retrofit.Builder().baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTweetsyAPI(retrofit: Retrofit): TweetsyAPI{
        return  retrofit.create(TweetsyAPI::class.java)
    }
}