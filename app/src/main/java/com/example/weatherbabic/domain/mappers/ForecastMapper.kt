package com.example.weatherbabic.domain.mappers

import com.example.weatherbabic.data.database.entities.Forecast
import com.example.weatherbabic.data.remote.response.open_weather.ForecastResponse
import com.example.weatherbabic.utils.*

fun ForecastResponse.mapToForecast(): List<Forecast> {
    var formattedForecastList = mutableListOf<Forecast>()
    val forecastResponseList = this.list ?: return formattedForecastList

    formattedForecastList = forecastResponseList
        .asSequence()
        .map {
            val iconCode = it.weather?.get(0)?.icon ?: EMPTY_STRING
            val code = iconCode.replace("n", "d")
            val temp = it.main?.temp ?: 0.0
            val windDirection = it.wind?.deg ?: 0
            val timeStamp = it.dt ?: 0
            Forecast(
                date = getDate(timeStamp),
                icon = getWeatherIcon(code),
                temperature = formatTemperature(temp),
                windDirection = getWindDirectionIcon(windDirection),
                windSpeed = it.wind?.speed ?: 0.0
            )
        }.distinctBy {
            it.date
        }.toMutableList()

    return formattedForecastList
}