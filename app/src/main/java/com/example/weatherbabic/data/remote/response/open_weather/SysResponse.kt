package com.example.weatherbabic.data.remote.response.open_weather

data class SysResponse(
    val country: String?,
    val message: Double?,
    val sunrise: Long?,
    val sunset: Long?
)