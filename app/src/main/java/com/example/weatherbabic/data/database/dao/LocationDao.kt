package com.example.weatherbabic.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherbabic.data.database.entities.Location

@Dao
interface LocationDao {

    @Query("SELECT * FROM location")
    fun getLocations(): LiveData<List<Location>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLocation(location: Location)
}