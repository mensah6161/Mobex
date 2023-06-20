package com.example.group401

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.group401.R

class FullVersionActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_VIDEO_DEEP_LINK = "extra_video_deep_link"
    }

    private lateinit var videoView: VideoView
    private lateinit var playPauseButton: ImageButton
    private lateinit var replayButton: ImageButton
    private lateinit var closeButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_version)

        // Hide the status bar
        hideStatusBar()

        // Set the background color to black
        setWindowBackgroundBlack()

        videoView = findViewById(R.id.fullVideoView)
        playPauseButton = findViewById(R.id.playPauseButton)
        replayButton = findViewById(R.id.replayButton)
        closeButton = findViewById(R.id.closeButton)

        val videoDeepLink = intent.getStringExtra(EXTRA_VIDEO_DEEP_LINK)

        // Set video URI for the VideoView
        val videoUri = Uri.parse(videoDeepLink)
        videoView.setVideoURI(videoUri)

        // Set click listener for play/pause button
        playPauseButton.setOnClickListener {
            if (videoView.isPlaying) {
                pauseVideo()
            } else {
                playVideo()
            }
        }

        // Set click listener for replay button
        replayButton.setOnClickListener {
            replayVideo()
        }

        // Set click listener for close button
        closeButton.setOnClickListener {
            finish()
        }

        // Set completion listener for video playback
        videoView.setOnCompletionListener {
            showReplayButton()
        }

        // Start playing the video
        playVideo()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop and release the VideoView resources when the activity is destroyed
        videoView.stopPlayback()
    }

    private fun playVideo() {
        videoView.start()
        playPauseButton.setImageResource(R.drawable.ic_pause)
    }

    private fun pauseVideo() {
        videoView.pause()
        playPauseButton.setImageResource(R.drawable.ic_play)
    }

    private fun replayVideo() {
        videoView.seekTo(0)
        playVideo()
        hideReplayButton()
    }

    private fun showReplayButton() {
        replayButton.visibility = View.VISIBLE
    }

    private fun hideReplayButton() {
        replayButton.visibility = View.GONE
    }

    private fun hideStatusBar() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.statusBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    private fun setWindowBackgroundBlack() {
        window.decorView.setBackgroundColor(resources.getColor(android.R.color.black))
    }
}
