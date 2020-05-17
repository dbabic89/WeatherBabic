package com.example.weatherbabic.di

import android.app.Application
import com.example.weatherbabic.WeatherBabicApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        OpenWeatherRetrofitModule::class,
        YoutubeRetrofitModule::class,
        ViewModelModule::class]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: WeatherBabicApplication)
}