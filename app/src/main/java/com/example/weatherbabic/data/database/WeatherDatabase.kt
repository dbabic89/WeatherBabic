package com.example.weatherbabic.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherbabic.data.database.dao.ForecastDao
import com.example.weatherbabic.data.database.dao.LocationDao
import com.example.weatherbabic.data.database.dao.WeatherDao
import com.example.weatherbabic.data.database.entities.Forecast
import com.example.weatherbabic.data.database.entities.Location
import com.example.weatherbabic.data.database.entities.Weather

@Database(
    entities = [
        Weather::class,
        Forecast::class,
        Location::class
    ],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    abstract fun forecastDao(): ForecastDao

    abstract fun locationDao(): LocationDao
}