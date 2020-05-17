package com.example.weatherbabic.data.remote.response.youtube

data class YoutubeSearchResponse(
    val etag: String?,
    val items: List<ItemResponse>?,
    val kind: String?,
    val nextPageToken: String?,
    val pageInfo: PageInfoResponse?,
    val regionCode: String?
)