package com.example.weatherbabic.data.remote.response.open_weather


data class DailyForecastResponse(
    val dt: Long?,
    val dt_txt: String?,
    val main: MainResponse?,
    val weather: List<WeatherDescription>?,
    val wind: WindResponse?
)