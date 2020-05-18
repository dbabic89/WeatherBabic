package com.example.weatherbabic.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherbabic.di.ViewModelKey
import com.example.weatherbabic.utils.factory.ViewModelFactory
import com.example.weatherbabic.ui.weather.WeatherDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WeatherDetailViewModel::class)
    abstract fun bindWeatherDetailViewModel(weatherDetailViewModel: WeatherDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}