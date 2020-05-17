package com.example.weatherbabic.domain.mappers

import com.example.weatherbabic.data.database.entities.Weather
import com.example.weatherbabic.data.remote.response.open_weather.CurrentWeatherResponse
import com.example.weatherbabic.utils.EMPTY_STRING
import com.example.weatherbabic.utils.getWeatherIcon
import com.example.weatherbabic.utils.getWindDirectionIcon

fun CurrentWeatherResponse.mapToWeather(): Weather {
    val weatherResponse = this.weather?.get(0)
    val mainResponse = this.main
    val windResponse = this.wind
    val sysResponse = this.sys
    val iconCode = weatherResponse?.icon ?: EMPTY_STRING
    val windDirection = windResponse?.deg ?: 1000
    return Weather(
        cityName = this.name ?: EMPTY_STRING,
        icon = getWeatherIcon(iconCode),
        description = weatherResponse?.description ?: EMPTY_STRING,
        currentTemp = mainResponse?.temp ?: 0.0,
        feelsLike = mainResponse?.feels_like ?: 0.0,
        maxTemp = mainResponse?.temp_max ?: 0.0,
        minTemp = mainResponse?.temp_min ?: 0.0,
        windSpeed = windResponse?.speed ?: 0.0,
        windDirection = getWindDirectionIcon(windDirection),
        humidity = mainResponse?.humidity ?: 0,
        pressure = mainResponse?.pressure ?: 0.0,
        sunriseTime = sysResponse?.sunrise ?: 0,
        sunsetTime = sysResponse?.sunset ?: 0,
        youtubeQuery = "${weatherResponse?.description} +${this.name}"
    )
}