package com.example.weatherbabic.data.database.entities

import androidx.room.Entity
import java.io.Serializable

@Entity
data class YoutubeVideo(
    val key: String,
    val title: String,
    val description: String
) : Serializable