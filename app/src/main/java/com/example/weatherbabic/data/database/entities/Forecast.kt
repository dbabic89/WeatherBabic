package com.example.weatherbabic.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Forecast(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_DATE)
    val date: String,

    @ColumnInfo(name = COLUMN_ICON)
    val icon: Int,

    @ColumnInfo(name = COLUMN_TEMPERATURE)
    val temperature: String,

    @ColumnInfo(name = COLUMN_WIND_DIRECTION)
    val windDirection: String,

    @ColumnInfo(name = COLUMN_WIND_SPEED)
    val windSpeed: Double
) : Serializable {
    companion object {
        const val COLUMN_DATE = "date"
        const val COLUMN_ICON = "icon"
        const val COLUMN_TEMPERATURE = "temperature"
        const val COLUMN_WIND_DIRECTION = "wind_direction"
        const val COLUMN_WIND_SPEED = "wind_speed"
    }
}