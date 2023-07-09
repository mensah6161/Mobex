package com.example.group401

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoPlayerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_VIDEO_TITLE = "extra_video_title"
        const val EXTRA_VIDEO_THUMBNAIL = "extra_video_thumbnail"
        const val EXTRA_VIDEO_DEEP_LINK = "extra_video_deep_link"
        const val VIDEO_SIZE= "extra_video_size"
        const val CREATED="extra_created"
        const val INSTITUTION="extra_institution"
        const val INSTITUTION_LOGO="extra_institution_logo"
        const val PUBLISHER="extra_publisher"
        const val  DURATION="extra_duration"
        const val CATEGORY="extra_category"
        const val SUBCATEGORY="extra_subcategory"
        const val AV_FROM="extra_date_from"
        const val AV_UNTIL="extra_date_until"
        const val CHILD_FRIENDLY="extra_child_friendly"


    }

    private lateinit var videoView: VideoView
    private lateinit var videoTitleTextView: TextView
    // private lateinit var videoThumbnailImageView: ImageView optional für später
    private lateinit var fullScreenButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_player)

        videoTitleTextView = findViewById(R.id.Title)
        videoTitleTextView.setText(EXTRA_VIDEO_TITLE)
        // videoThumbnailImageView = findViewById(R.id.videoThumbnailImageView)
        fullScreenButton = findViewById(R.id.vollbild)
        videoView = findViewById(R.id.videoView2)

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
            intent.putExtra(FullVersionActivity.EXTRA_VIDEO_TITLE, videoTitle)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop vid and close everything depending on it/recources
        videoView.stopPlayback()
    }
}
