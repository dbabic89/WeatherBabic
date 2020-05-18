package com.example.weatherbabic.di.modules

import com.example.weatherbabic.di.modules.FragmentBuilderModule
import com.example.weatherbabic.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity
}