package com.example.weatherbabic.utils

import com.example.weatherbabic.R

const val DAY_CLEAR_SKY = "01d"
const val DAY_FEW_CLOUDS = "02d"
const val DAY_SCATTERED_CLOUDS = "03d"
const val DAY_BROKEN_CLOUDS = "04d"
const val DAY_SHOWER_RAIN = "09d"
const val DAY_RAIN = "10d"
const val DAY_THUNDERSTORM = "11d"
const val DAY_SNOW = "13d"
const val DAY_MIST = "50d"

const val NIGHT_CLEAR_SKY = "01n"
const val NIGHT_FEW_CLOUDS = "02n"
const val NIGHT_SCATTERED_CLOUDS = "03n"
const val NIGHT_BROKEN_CLOUDS = "04n"
const val NIGHT_SHOWER_RAIN = "09n"
const val NIGHT_RAIN = "10n"
const val NIGHT_THUNDERSTORM = "11n"
const val NIGHT_SNOW = "13n"
const val NIGHT_MIST = "50n"

fun getWeatherIcon(iconCode: String): Int {
    return when (iconCode) {
        DAY_CLEAR_SKY -> R.drawable.day_clear_sky
        DAY_FEW_CLOUDS -> R.drawable.day_few_clouds
        DAY_SCATTERED_CLOUDS -> R.drawable.day_clouds
        DAY_BROKEN_CLOUDS -> R.drawable.day_broken_clouds
        DAY_SHOWER_RAIN -> R.drawable.day_shower_rain
        DAY_RAIN -> R.drawable.day_rain
        DAY_THUNDERSTORM -> R.drawable.day_thunderstorm
        DAY_SNOW -> R.drawable.day_snow
        DAY_MIST -> R.drawable.day_mist
        NIGHT_CLEAR_SKY -> R.drawable.night_clear_sky
        NIGHT_FEW_CLOUDS -> R.drawable.night_few_clouds
        NIGHT_SCATTERED_CLOUDS -> R.drawable.night_clouds
        NIGHT_BROKEN_CLOUDS -> R.drawable.night_broken_clouds
        NIGHT_SHOWER_RAIN -> R.drawable.night_shower_rain
        NIGHT_RAIN -> R.drawable.night_rain
        NIGHT_THUNDERSTORM -> R.drawable.night_thunderstorm
        NIGHT_SNOW -> R.drawable.night_snow
        NIGHT_MIST -> R.drawable.night_mist
        else -> R.drawable.day_clear_sky
    }
}

fun getWindDirectionIcon(direction: Int) : String {
    if (direction < 22.5) return NORTH_EAST
    if (direction < 67.5) return EAST
    if (direction < 122.5) return SOUTH_EAST
    if (direction < 157.5) return SOUTH
    if (direction < 202.5) return SOUTH_WEST
    if (direction < 247.5) return WEST
    if (direction < 292.5) return NORTH_WEST
    if (direction < 337.5) return NORTH
    return POINT
}