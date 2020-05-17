package com.example.weatherbabic

import android.os.Bundle
import com.example.weatherbabic.data.database.entities.YoutubeVideo
import com.example.weatherbabic.data.remote.YoutubeService
import com.example.weatherbabic.utils.ARGUMENT_YOUTUBE_VIDEO
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity : YouTubeBaseActivity() {

    var isFullscreen = false
    var youtubeVideo: YoutubeVideo? = null
    lateinit var player: YouTubePlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        youtubeVideo = intent.extras?.getSerializable(ARGUMENT_YOUTUBE_VIDEO) as? YoutubeVideo

        youtubePlayer.initialize(
            YoutubeService.YOUTUBE_DATA_API_KEY, object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    youtubePlayerProvider: YouTubePlayer.Provider?,
                    youtubePlayer: YouTubePlayer?,
                    p2: Boolean
                ) {
                    youtubePlayer?.let {
                        player = it
                        it.loadVideo(youtubeVideo?.key)
                        it.setOnFullscreenListener { isVideoFullscreen ->
                            isFullscreen = isVideoFullscreen
                        }
                    }
                }

                override fun onInitializationFailure(
                    youtubePlayerProvider: YouTubePlayer.Provider?,
                    result: YouTubeInitializationResult?
                ) {

                }
            }
        )
    }

    override fun onBackPressed() {
        if (isFullscreen) {
            player.setFullscreen(false)
        } else {
            super.onBackPressed()
        }
    }
}