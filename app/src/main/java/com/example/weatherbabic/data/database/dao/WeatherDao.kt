package com.example.weatherbabic.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherbabic.data.database.entities.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCurrentWeather(weather: Weather)

    @Query("DELETE FROM weather")
    fun clearCurrentWeather()

    @Query("SELECT * FROM weather")
    fun getCurrentWeather(): LiveData<Weather>
}