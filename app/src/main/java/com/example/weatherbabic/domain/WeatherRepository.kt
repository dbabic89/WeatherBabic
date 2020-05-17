package com.example.weatherbabic.domain

import com.example.weatherbabic.data.database.DatabaseDataSource
import com.example.weatherbabic.data.database.entities.Forecast
import com.example.weatherbabic.data.database.entities.Location
import com.example.weatherbabic.data.database.entities.Weather
import com.example.weatherbabic.data.database.entities.YoutubeVideo
import com.example.weatherbabic.data.remote.NetworkDataSource
import com.example.weatherbabic.domain.mappers.mapToForecast
import com.example.weatherbabic.domain.mappers.mapToWeather
import com.example.weatherbabic.domain.mappers.mapToYoutubeVideo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepository
@Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val databaseDataSource: DatabaseDataSource
) {

    fun fetchCurrentWeatherByLocation(latitude: Double, longitude: Double): Observable<Weather> {
        return networkDataSource
            .fetchCurrentWeatherByCoordinates(latitude, longitude)
            .toObservable()
            .map { it.mapToWeather() }
            .doOnNext {
                it?.let {
                    databaseDataSource.saveCurrentWeather(it)
                    databaseDataSource.saveLocation(Location(it.cityName))
                }
            }
    }

    fun fetchForecastByLocation(latitude: Double, longitude: Double): Observable<List<Forecast>> {
        return networkDataSource
            .fetchForecastByCoordinates(latitude, longitude)
            .toObservable()
            .map { it.mapToForecast() }
            .doOnNext {
                it?.let {
                    it.forEach { forecast ->
                        databaseDataSource.saveForecast(forecast)
                    }
                }
            }
    }

    fun fetchCurrentWeatherByName(location: String): Observable<Weather> {
        return networkDataSource
            .fetchCurrentWeather(location)
            .toObservable()
            .map { it.mapToWeather() }
            .doOnNext {
                it?.let {
                    databaseDataSource.saveCurrentWeather(it)
                    databaseDataSource.saveLocation(Location(it.cityName))
                }
            }
    }

    fun fetchForecastByName(location: String): Observable<List<Forecast>> {
        return networkDataSource
            .fetchForecastByName(location)
            .toObservable()
            .map { it.mapToForecast() }
            .doOnNext {
                it?.let {
                    it.forEach { forecast ->
                        databaseDataSource.saveForecast(forecast)
                    }
                }
            }
    }

    fun getLocations() = databaseDataSource.getLocations()

    fun searchYoutubeVideos(query: String): Single<YoutubeVideo> {
        return networkDataSource
            .searchYoutubeVideos(query)
            .map {
                it.mapToYoutubeVideo()
            }
    }
}