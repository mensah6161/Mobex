package com.example.group401

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.group401.R

class VideoPlayerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_VIDEO_TITLE = "extra_video_title"
        const val EXTRA_VIDEO_THUMBNAIL = "extra_video_thumbnail"
        const val EXTRA_VIDEO_DEEP_LINK = "extra_video_deep_link"
    }

    private lateinit var videoView: VideoView
    private lateinit var videoTitleTextView: TextView
    // private lateinit var videoThumbnailImageView: ImageView optional für später
    private lateinit var fullScreenButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        videoTitleTextView = findViewById(R.id.videoTitleTextView)
        // videoThumbnailImageView = findViewById(R.id.videoThumbnailImageView)
        fullScreenButton = findViewById(R.id.fullScreenButton)
        videoView = findViewById(R.id.videoView)

        val videoTitle = intent.getStringExtra(EXTRA_VIDEO_TITLE)
        //val videoThumbnail = intent.getIntExtra(EXTRA_VIDEO_THUMBNAIL, R.drawable.thumbfam1)
        val videoDeepLink = intent.getStringExtra(EXTRA_VIDEO_DEEP_LINK)

        videoTitleTextView.text = videoTitle
        // videoThumbnailImageView.setImageResource(videoThumbnail)

        // Set video URI for the VideoView
        val videoUri = Uri.parse(videoDeepLink)
        videoView.setVideoURI(videoUri)

        // Starting video
        videoView.start()

        // Full screen Option
        fullScreenButton.setOnClickListener {
            val intent = Intent(this, FullVersionActivity::class.java)
            intent.putExtra(FullVersionActivity.EXTRA_VIDEO_DEEP_LINK, videoDeepLink)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop vid and close everything depending on it/recources
        videoView.stopPlayback()
    }
}
