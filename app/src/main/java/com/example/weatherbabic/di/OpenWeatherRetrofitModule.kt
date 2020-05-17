package com.example.weatherbabic.di

import com.example.weatherbabic.data.remote.OpenWeatherService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Suppress("unused")
@Module
object OpenWeatherRetrofitModule {

    private const val OPEN_WEATHER_BASE_URL = "https://api.openweathermap.org/"

    @Provides
    @Reusable
    @JvmStatic
    @Named("openWeather")
    internal fun openWeatherRetrofit(okHttpClient: OkHttpClient, gson: Gson) =
        Retrofit.Builder()
            .baseUrl(OPEN_WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient).build()

    @Provides
    @Reusable
    @JvmStatic
    internal fun openWeatherService(@Named("openWeather") retrofit: Retrofit) =
        retrofit.create(OpenWeatherService::class.java)
}