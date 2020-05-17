package com.example.weatherbabic.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Weather(
    @PrimaryKey @ColumnInfo(name = COLUMN_CITY_NAME) val cityName: String,
    @ColumnInfo(name = COLUMN_ICON) val icon: Int,
    @ColumnInfo(name = COLUMN_DESCRIPTION) val description: String,
    @ColumnInfo(name = COLUMN_CURRENT_TEMP) val currentTemp: Double,
    @ColumnInfo(name = COLUMN_FEELS_LIKE) val feelsLike: Double,
    @ColumnInfo(name = COLUMN_MAX_TEMP) val maxTemp: Double,
    @ColumnInfo(name = COLUMN_MIN_TEMP) val minTemp: Double,
    @ColumnInfo(name = COLUMN_WIND_SPEED) val windSpeed: Double,
    @ColumnInfo(name = COLUMN_WIND_DIRECTION) val windDirection: String,
    @ColumnInfo(name = COLUMN_HUMIDITY) val humidity: Int,
    @ColumnInfo(name = COLUMN_PRESSURE) val pressure: Double,
    @ColumnInfo(name = COLUMN_SUNRISE_TIME) val sunriseTime: Long,
    @ColumnInfo(name = COLUMN_SUNSET_TIME) val sunsetTime: Long,
    @ColumnInfo(name = COLUMN_YOUTUBE_QUERY) val youtubeQuery: String
) : Serializable {
    companion object {
        const val COLUMN_CITY_NAME = "cityName"
        const val COLUMN_ICON = "icon"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_CURRENT_TEMP = "current_temp"
        const val COLUMN_FEELS_LIKE = "feels_like"
        const val COLUMN_MAX_TEMP = "max_temp"
        const val COLUMN_MIN_TEMP = "min_temp"
        const val COLUMN_WIND_SPEED = "wind_speed"
        const val COLUMN_WIND_DIRECTION = "wind_direction_icon"
        const val COLUMN_HUMIDITY = "humidity"
        const val COLUMN_PRESSURE = "pressure"
        const val COLUMN_SUNRISE_TIME = "sunrise_time"
        const val COLUMN_SUNSET_TIME = "sunset_time"
        const val COLUMN_YOUTUBE_QUERY = "youtube_query"
    }
}
