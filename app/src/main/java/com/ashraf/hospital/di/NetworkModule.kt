package com.ashraf.hospital.di

import com.ashraf.hospital.data.remote.service.HospitalApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://api.instant-ss.com/api/v1/"

    @Singleton
    @Provides
    fun provideHospitalApiService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): HospitalApiService {
        return provideRetrofit(BASE_URL, okHttpClient, gsonConverterFactory)
            .create(HospitalApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        url: String,
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    private const val TIMEOUT = 30L

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(loggingInterceptor)
        }
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}