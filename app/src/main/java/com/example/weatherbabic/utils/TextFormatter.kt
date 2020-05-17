package com.example.weatherbabic.utils

import android.content.Context
import com.example.weatherbabic.R
import com.example.weatherbabic.utils.Units.IMPERIAL
import com.example.weatherbabic.utils.Units.METRIC

const val KELVIN_UNIT = "K"
const val KILOMETER_PER_HOUR_UNIT = "km/h"
const val MILE_PER_HOUR_UNIT = "mph"
const val HECTO_PASCAL_UNIT = "hPa"

const val DEGREE_SYMBOL = "°"
const val PERCENT_SYMBOL = "%"

enum class Units {
    METRIC,
    IMPERIAL
}

fun formatTemperature(temperature: Double, unit: Units = METRIC): String {
    val temp = temperature.toInt()
    return when (unit) {
        METRIC -> "$temp$DEGREE_SYMBOL"
        IMPERIAL -> "$temp$KELVIN_UNIT"
    }
}

fun formatFeelsLike(context: Context?, temperature: Double): String {
    if (context == null) return EMPTY_STRING
    val formattedTemperature = formatTemperature(temperature)
    return String.format(context.getString(R.string.feels_like), formattedTemperature)
}

fun formatSunriseSunset(context: Context?, sunrise: Long, sunset: Long): String {
    if (context == null) return EMPTY_STRING

    val rise = getTime(sunrise)
    val set = getTime(sunset)

    val formattedSunrise = String.format(context.getString(R.string.sunrise), rise)
    val formattedSunset = String.format(context.getString(R.string.sunset), set)

    return String.format(
        context.getString(R.string.concat_two_strings_slash),
        formattedSunrise,
        formattedSunset
    )
}

fun getTemperatureSymbol(unit: Units = METRIC): String {
    return when (unit) {
        METRIC -> DEGREE_SYMBOL
        IMPERIAL -> KELVIN_UNIT
    }
}

fun formatWindSpeed(windSpeed: Double, unit: Units = METRIC): String {
    val speed = windSpeed.toInt()
    return when (unit) {
        METRIC -> "$speed $KILOMETER_PER_HOUR_UNIT"
        IMPERIAL -> "$speed $MILE_PER_HOUR_UNIT"
    }
}

fun formatPressure(pressure: Double) = "$pressure $HECTO_PASCAL_UNIT"

fun formatPercent(number: Int) = "$number$PERCENT_SYMBOL"