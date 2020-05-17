package com.example.weatherbabic.data.remote

import com.example.weatherbabic.data.remote.response.open_weather.CurrentWeatherResponse
import com.example.weatherbabic.data.remote.response.open_weather.ForecastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

    companion object {
        const val API_KEY = "82425a528f72f6646c1ab03f6b18f7c5"
    }

    @GET("/data/2.5/weather")
    fun getCurrentWeatherByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Single<CurrentWeatherResponse>

    @GET("/data/2.5/weather")
    fun getCurrentWeatherByName(
        @Query("q") location: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Single<CurrentWeatherResponse>

    @GET("/data/2.5/forecast")
    fun getForecastByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Single<ForecastResponse>

    @GET("/data/2.5/forecast")
    fun getForecastByName(
        @Query("q") location: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Single<ForecastResponse>
}