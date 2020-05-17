package com.example.weatherbabic.data.remote.response.open_weather

data class CurrentWeatherResponse(
    val base: String?,
    val dt: Long?,
    val id: Int?,
    val main: MainResponse?,
    val name: String?,
    val sys: SysResponse?,
    val weather: List<WeatherDescription>?,
    val wind: WindResponse?
)