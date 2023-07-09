package com.example.group401

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

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
        const val  DURATION=45
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
    private  lateinit var description: TextView
    private  lateinit var Institution_Logo:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_player)
        var Text:String


        Text= "# "+ intent.getStringExtra(CATEGORY) +"#"+ intent.getStringExtra(SUBCATEGORY) +", Duratio:"
        videoTitleTextView = findViewById(R.id.Title)
        description=findViewById(R.id.Description)
        Institution_Logo=findViewById(R.id.imageView)
        videoTitleTextView.setText(EXTRA_VIDEO_TITLE)
        // videoThumbnailImageView = findViewById(R.id.videoThumbnailImageView)
        fullScreenButton = findViewById(R.id.vollbild)
        videoView = findViewById(R.id.videoView2)

        val videoTitle = intent.getStringExtra(EXTRA_VIDEO_TITLE)
        //val videoThumbnail = intent.getIntExtra(EXTRA_VIDEO_THUMBNAIL, R.drawable.thumbfam1)
        val videoDeepLink = intent.getStringExtra(EXTRA_VIDEO_DEEP_LINK)

        val target = object : CustomTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                Institution_Logo.setImageDrawable(resource)
            }
            override fun onLoadFailed(errorDrawable: Drawable?) {
            }
            override fun onLoadCleared(placeholder: Drawable?) {
            }
        }
        Glide.with(videoView)
            .load(INSTITUTION_LOGO)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(target)




        videoTitleTextView.text = videoTitle
        description.text= Text
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
