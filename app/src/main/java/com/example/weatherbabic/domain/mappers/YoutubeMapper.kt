package com.example.weatherbabic.domain.mappers

import com.example.weatherbabic.data.database.entities.YoutubeVideo
import com.example.weatherbabic.data.remote.response.youtube.YoutubeSearchResponse
import com.example.weatherbabic.utils.EMPTY_STRING

fun YoutubeSearchResponse.mapToYoutubeVideo(): YoutubeVideo? {
    if (this.items.isNullOrEmpty()) return null
    val item = this.items[0]

    val key = item.id?.videoId ?: return null
    val title = item.snippetResponse?.title ?: EMPTY_STRING
    val description = item.snippetResponse?.description ?: EMPTY_STRING

    return YoutubeVideo(
        key = key,
        title = title,
        description = description
    )

}