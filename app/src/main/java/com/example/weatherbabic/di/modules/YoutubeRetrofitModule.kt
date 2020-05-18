package com.example.weatherbabic.di.modules

import com.example.weatherbabic.data.remote.YoutubeService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Suppress("unused")
@Module
object YoutubeRetrofitModule {

    private const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/"

    @Provides
    @Reusable
    @JvmStatic
    @Named("youtube")
    internal fun youtubeRetrofit(okHttpClient: OkHttpClient, gson: Gson) =
        Retrofit.Builder()
            .baseUrl(YOUTUBE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient).build()

    @Provides
    @Reusable
    @JvmStatic
    internal fun youtubeService(@Named("youtube") retrofit: Retrofit) =
        retrofit.create(YoutubeService::class.java)
}