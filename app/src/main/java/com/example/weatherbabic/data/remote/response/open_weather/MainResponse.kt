package com.example.weatherbabic.data.remote.response.open_weather

data class MainResponse(
    val humidity: Int?,
    val pressure: Double?,
    val temp: Double?,
    val feels_like: Double?,
    val temp_max: Double?,
    val temp_min: Double?
)