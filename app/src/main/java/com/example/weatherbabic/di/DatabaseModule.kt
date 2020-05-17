package com.example.weatherbabic.di

import android.app.Application
import androidx.room.Room
import com.example.weatherbabic.data.database.dao.LocationDao
import com.example.weatherbabic.data.database.dao.WeatherDao
import com.example.weatherbabic.data.database.WeatherDatabase
import com.example.weatherbabic.data.database.dao.ForecastDao
import com.example.weatherbabic.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Suppress("unused")
@Module
object DatabaseModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun database(application: Application): WeatherDatabase {
        return Room.databaseBuilder(
            application,
            WeatherDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun weatherDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.weatherDao()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun forecastDao(weatherDatabase: WeatherDatabase): ForecastDao {
        return weatherDatabase.forecastDao()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun locationDao(weatherDatabase: WeatherDatabase): LocationDao {
        return weatherDatabase.locationDao()
    }
}