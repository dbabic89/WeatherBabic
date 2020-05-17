package com.example.weatherbabic.data.remote

import com.example.weatherbabic.data.remote.response.youtube.YoutubeSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    companion object {
        const val YOUTUBE_DATA_API_KEY = "AIzaSyAZBu0HItQfbYREchi01kDQYTqKAfNo1Nc"
    }

    @GET("search?part=snippet&maxResults=25")
    fun searchYoutubeVideos(
        @Query("q") query: String,
        @Query("key") apiKey: String
    ): Single<YoutubeSearchResponse>
}