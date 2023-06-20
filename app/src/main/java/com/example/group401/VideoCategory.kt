package com.example.group401


import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.content.Intent

class VideoCategory(private val categoryName: String, val videoList: List<VideoItem>) {
    var deeplink: String = "" // Deeplink-Eigenschaft hinzufügen
    fun createView(layoutInflater: LayoutInflater, parentLayout: LinearLayout) {

        val categoryView = layoutInflater.inflate(R.layout.category_layout, null)
        val categoryNameTextView = categoryView.findViewById<TextView>(R.id.categoryName)
        val videoLayout = categoryView.findViewById<LinearLayout>(R.id.videoLayout)
        val scrollView = categoryView.findViewById<HorizontalScrollView>(R.id.scrollView)

        categoryNameTextView.text = categoryName

        for (video in videoList) {
            val videoView = layoutInflater.inflate(R.layout.item_video, null)
            val thumbnailImageView = videoView.findViewById<ImageView>(R.id.thumbnailImageView)
            val titleTextView = videoView.findViewById<TextView>(R.id.titleTextView)

            thumbnailImageView.setImageResource(video.thumbnail)
            titleTextView.text = video.title

            videoView.setOnClickListener {
                val intent = Intent(videoView.context, VideoPlayerActivity::class.java)
                intent.putExtra("videoTitle", video.title)
                intent.putExtra("videoDeepLink", video.deepLink)
                videoView.context.startActivity(intent)
            }





            videoLayout.addView(videoView)
        }

        parentLayout.addView(categoryView)
        scrollView.post {
            scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
        }
    }
}
