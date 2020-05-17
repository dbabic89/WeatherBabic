package com.example.weatherbabic.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherbabic.data.database.entities.Forecast

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveForecast(forecast: Forecast)

    @Query("SELECT * FROM forecast")
    fun getForecast(): LiveData<List<Forecast>>
}