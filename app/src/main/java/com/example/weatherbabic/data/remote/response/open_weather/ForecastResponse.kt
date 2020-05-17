package com.example.weatherbabic.data.remote.response.open_weather

data class ForecastResponse(
    val city: CityResponse?,
    val cod: String?,
    val list: List<DailyForecastResponse>?
)

