package com.example.weatherbabic.data.database

import com.example.weatherbabic.data.database.dao.ForecastDao
import com.example.weatherbabic.data.database.dao.LocationDao
import com.example.weatherbabic.data.database.dao.WeatherDao
import com.example.weatherbabic.data.database.entities.Forecast
import com.example.weatherbabic.data.database.entities.Location
import com.example.weatherbabic.data.database.entities.Weather
import io.reactivex.Completable
import javax.inject.Inject

class DatabaseDataSource
@Inject constructor(
    private val weatherDao: WeatherDao,
    private val locationDao: LocationDao,
    private val forecastDao: ForecastDao
) {

    fun saveCurrentWeather(weather: Weather): Completable {
        return Completable.fromCallable {
            weatherDao.clearCurrentWeather()
            weatherDao.saveCurrentWeather(weather)
            locationDao.saveLocation(Location(weather.cityName))
        }
    }

    fun saveForecast(forecast: Forecast) {
        forecastDao.saveForecast(forecast)
    }

    fun getLocations() = locationDao.getLocations()

    fun saveLocation(location: Location) {
        locationDao.saveLocation(location)
    }
}