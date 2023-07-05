package com.example.group401

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.net.URL
class VideoListGenerator {
    companion object {
        fun getCategories(): List<Category> {
            // Generate and return the list of categories dynamically here
            val categories = mutableListOf<Category>()
            val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/Morelasci.jpg/300px-Morelasci.jpg"
            // Add categories and their video items
            val sealifeVideos = listOf(
                VideoItem("Video First", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "https://test-videos.co.uk/vids/jellyfish/mp4/h264/1080/Jellyfish_1080_10s_1MB.mp4"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_2"),
                VideoItem("Video 3", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_3"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_4"),
                VideoItem("Video 3", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_5"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_6"),
                VideoItem("Video 3", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_7"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_8"),
                VideoItem("Video 3","https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_9")
            )
            val workVideos = listOf(
                VideoItem("Video First", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "https://test-videos.co.uk/vids/jellyfish/mp4/h264/1080/Jellyfish_1080_10s_1MB.mp4"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_2"),
                VideoItem("Video 3", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_3"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_4"),
                VideoItem("Video 3", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_5"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_6"),
                VideoItem("Video 3", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_7"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_8"),
                VideoItem("Video 3","https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_9")
            )
            val familyVideos = listOf(
                VideoItem("Video First", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "https://test-videos.co.uk/vids/jellyfish/mp4/h264/1080/Jellyfish_1080_10s_1MB.mp4"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_2"),
                VideoItem("Video 3", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_3"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_4"),
                VideoItem("Video 3", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_5"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_6"),
                VideoItem("Video 3", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_7"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_8"),
                VideoItem("Video 3","https://api.ardmediathek.de/image-service/images/urn:ard:image:38abcf713cbc2935?w=200&ch=18fa4e9974aa0d6c&imwidth=200", "DEEP_LINK_9")
            )

            // Add categories to the list
            categories.add(Category("Sealife", sealifeVideos))
            categories.add(Category("Work", workVideos))
            categories.add(Category("Family", familyVideos))

            return categories
        }

    }
}