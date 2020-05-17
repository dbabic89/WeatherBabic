package com.example.weatherbabic.data.remote.response.youtube

data class SnippetResponse(
    val channelId: String?,
    val channelTitle: String?,
    val description: String?,
    val liveBroadcastContent: String?,
    val publishTime: String?,
    val publishedAt: String?,
    val thumbnailsResponse: ThumbnailsResponse?,
    val title: String?
)