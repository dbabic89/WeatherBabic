package com.example.weatherbabic.di

import android.util.Log
import com.example.weatherbabic.utils.WEATHER_APP
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Suppress("unused")
@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.i(WEATHER_APP, message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun gSon() = Gson()
}