package com.example.weatherbabic.data.remote

import com.example.weatherbabic.data.remote.OpenWeatherService.Companion.API_KEY
import com.example.weatherbabic.data.remote.YoutubeService.Companion.YOUTUBE_DATA_API_KEY
import com.example.weatherbabic.data.remote.response.open_weather.CurrentWeatherResponse
import com.example.weatherbabic.data.remote.response.open_weather.ForecastResponse
import io.reactivex.Single
import javax.inject.Inject

class NetworkDataSource
@Inject constructor(
    private val openWeatherService: OpenWeatherService,
    private val youtubeService: YoutubeService
) {

    fun fetchCurrentWeatherByCoordinates(
        latitude: Double,
        longitude: Double
    ): Single<CurrentWeatherResponse> {
        return openWeatherService
            .getCurrentWeatherByCoordinates(latitude, longitude, API_KEY, "metric")
    }

    fun fetchCurrentWeather(location: String): Single<CurrentWeatherResponse> {
        return openWeatherService
            .getCurrentWeatherByName(location, API_KEY, "metric")
    }

    fun fetchForecastByCoordinates(latitude: Double, longitude: Double): Single<ForecastResponse> {
        return openWeatherService
            .getForecastByCoordinates(latitude, longitude, API_KEY, "metric")
    }

    fun fetchForecastByName(location: String): Single<ForecastResponse> {
        return openWeatherService
            .getForecastByName(location, API_KEY, "metric")
    }

    fun searchYoutubeVideos(query: String) = youtubeService.searchYoutubeVideos(query, YOUTUBE_DATA_API_KEY)
}