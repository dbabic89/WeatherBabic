package com.example.weatherbabic.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherbabic.data.database.entities.Forecast
import com.example.weatherbabic.data.database.entities.Weather
import com.example.weatherbabic.data.database.entities.YoutubeVideo
import com.example.weatherbabic.domain.WeatherRepository
import com.example.weatherbabic.utils.EMPTY_STRING
import com.example.weatherbabic.utils.livedata.Resource
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherDetailViewModel
@Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    val weatherData: LiveData<Resource<Weather>> get() = _weatherData
    val forecastData: LiveData<Resource<List<Forecast>>> get() = _forecastData
    val youtubeData: LiveData<Resource<YoutubeVideo>> get() = _youtubeVideoData

    var favoriteLocationsData = repository.getLocations()

    private val _weatherData = MutableLiveData<Resource<Weather>>()
    private val _forecastData = MutableLiveData<Resource<List<Forecast>>>()
    private val _youtubeVideoData = MutableLiveData<Resource<YoutubeVideo>>()

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun fetchWeatherDataByLocation(latitude: Double, longitude: Double) {
        getCurrentWeatherByLocation(latitude, longitude)
        getForecastByLocation(latitude, longitude)
    }

    fun fetchWeatherDataByName(location: String) {
        getCurrentWeatherByName(location)
        getForecastByName(location)
    }

    private fun getCurrentWeatherByLocation(latitude: Double, longitude: Double) {
        repository.fetchCurrentWeatherByLocation(latitude, longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Weather> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                    _weatherData.value = Resource.success(null)
                }

                override fun onNext(weather: Weather) {
                    _weatherData.value = Resource.success(weather)
                }

                override fun onError(e: Throwable) {
                    val message = e.localizedMessage ?: EMPTY_STRING
                    _weatherData.value = Resource.error(message, null)
                }

            })
    }

    private fun getForecastByLocation(latitude: Double, longitude: Double) {
        repository.fetchForecastByLocation(latitude, longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Forecast>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                    _forecastData.value = Resource.loading(null)
                }

                override fun onNext(forecast: List<Forecast>) {
                    _forecastData.value = Resource.success(forecast)
                }

                override fun onError(e: Throwable) {
                    val message = e.localizedMessage ?: EMPTY_STRING
                    _forecastData.value = Resource.error(message, null)
                }

            })
    }

    private fun getCurrentWeatherByName(location: String) {
        repository.fetchCurrentWeatherByName(location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Weather> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                    _weatherData.value = Resource.success(null)
                }

                override fun onNext(weather: Weather) {
                    _weatherData.value = Resource.success(weather)
                }

                override fun onError(e: Throwable) {
                    val message = e.localizedMessage ?: EMPTY_STRING
                    _weatherData.value = Resource.error(message, null)
                }

            })
    }

    private fun getForecastByName(location: String) {
        repository.fetchForecastByName(location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Forecast>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                    _forecastData.value = Resource.loading(null)
                }

                override fun onNext(forecast: List<Forecast>) {
                    _forecastData.value = Resource.success(forecast)
                }

                override fun onError(e: Throwable) {
                    val message = e.localizedMessage ?: EMPTY_STRING
                    _forecastData.value = Resource.error(message, null)
                }

            })
    }

    fun searchYoutube(query: String) {
        repository.searchYoutubeVideos(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<YoutubeVideo> {
                override fun onSuccess(youtubeVideo: YoutubeVideo) {
                    _youtubeVideoData.value = Resource.success(youtubeVideo)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                    _youtubeVideoData.value = Resource.loading(null)
                }

                override fun onError(e: Throwable) {
                    val message = e.localizedMessage ?: EMPTY_STRING
                    _youtubeVideoData.value = Resource.error(message, null)
                }

            })
    }
}
