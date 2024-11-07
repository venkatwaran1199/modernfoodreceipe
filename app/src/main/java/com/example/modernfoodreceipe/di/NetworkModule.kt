package com.example.modernfoodreceipe.di

import com.example.modernfoodreceipe.utils.Constants.Companion.BASE_URL
import com.example.modernfoodreceipe.data.network.FoodReceipeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideokhttpclient():OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15,TimeUnit.SECONDS)
            .connectTimeout(15,TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providegsonconverterfactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideretrofitinstance(okHttpClient: OkHttpClient,
                                gsonConverterFactory: GsonConverterFactory):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideapiservices(retrofit: Retrofit): FoodReceipeApi {
        return retrofit.create(FoodReceipeApi::class.java)
    }
}