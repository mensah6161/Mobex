package com.example.group401

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import com.example.group401.API.APIClientRequest
import com.squareup.picasso.Picasso
import com.squareup.picasso.Request
import com.squareup.picasso.Target
import kotlinx.coroutines.runBlocking
import java.net.URL
class VideoListGenerator {
    companion object {
        fun getCategories(): List<Category> {
            // Generate and return the list of categories dynamically here
            val categories = mutableListOf<Category>()
            val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/Morelasci.jpg/300px-Morelasci.jpg"
            // Add categories and their video items
            val sealifeVideos = listOf(
                VideoItem("Video First", "https://api.ardmediathek.de/image-service/image-collections/urn:ard:image-collection:16a35d6891d34a4a/16x9?imwidth=1920&w=1920&impolicy=watermark", "https://rbbmediapmdp-a.akamaihd.net/content/81/92/81927b1f-1fb2-42c3-9303-38b3eeed8094/81927b1f-1fb2-42c3-9303-38b3eeed8094_hd1080-3500k.mp4"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/image-collections/urn:ard:image-collection:b4d348a8a37e406c/16x9?imwidth=1920&w=1920&impolicy=watermark", "https://cdn-storage.br.de/geo/MUJIuUOVBwQIbtC2uKJDM6OhuLnC_2rH_H1S/_-9S/_24g_AFg5U1S/c13d6c41-9d94-4c11-865c-32e262ba0fcb_C.mp4"),
                VideoItem("Video 3", "https://api.ardmediathek.de/image-service/image-collections/urn:ard:image-collection:e3231e79f8ecd754/16x9?imwidth=1920&w=1920&impolicy=watermark", "https://wdrmedien-a.akamaihd.net/medp/ondemand/weltweit/fsk0/290/2905687/2905687_51072128.mp4"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/image-collections/urn:ard:image-collection:d47a2d290233302a/16x9?imwidth=1920&w=1920&impolicy=watermark", "https://wdrmedien-a.akamaihd.net/medp/ondemand/weltweit/fsk0/290/2908140/2908140_51382661.mp4"),
                VideoItem("Video 3", "https://api.ardmediathek.de/image-service/image-collections/urn:ard:image-collection:a9001fc9804a0a82/16x9?imwidth=1920&w=1920&impolicy=watermark", "https://pdodswr-a.akamaihd.net/swrfernsehen/vollbild-recherchen-die-mehr-zeigen/1805739.ml.mp4"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/image-collections/urn:ard:image-collection:37d2ee88ec0ef74f/16x9?imwidth=1920&w=1920&impolicy=watermark", "https://pdodswr-a.akamaihd.net/kindernetz/sendungen/losgefragt/1383986.xl.mp4"),
                VideoItem("Video 3", "https://api.ardmediathek.de/image-service/image-collections/urn:ard:image-collection:3775f71b96205dd1/16x9?imwidth=1920&w=1920&impolicy=watermark", "https://hrardmediathek-vh.akamaihd.net/i/video/as/allewetter/2023_04/hrLogo_230414194625_L452624_,960x540-50p-1600,1920x1080-50p-5000,1280x720-50p-3200,640x360-50p-1200,480x270-50p-700,kbit.mp4.csmil/master.m3u8"),
                VideoItem("Video 2", "https://api.ardmediathek.de/image-service/image-collections/urn:ard:image-collection:dd74d35c1b9ad5f9/16x9?imwidth=1920&w=1920&impolicy=watermark", "https://cdn-storage.br.de/MUJIuUOVBwQIbtCCBLzGiLC1uwQoNA4p_2ZS/_-9S/_24g_2rd_U1S/553aa5ce-66bf-43e6-9451-5c3d950198c3_X.mp4"),
                VideoItem("Video 3","https://api.ardmediathek.de/image-service/image-collections/urn:ard:image-collection:84c83166f1ec4747/16x9?imwidth=1920&w=1920&impolicy=watermark", "https://wdrmedien-a.akamaihd.net/medp/ondemand/weltweit/fsk0/31/312342/312342_37772892.mp4")
            )
            val workVideos = mutableListOf<VideoItem>()

            val apiClient = APIClientRequest()

            val randomVideos = runBlocking { apiClient.getRandomVideos(1) }
            for (video in randomVideos) {
                val title = video.title ?: ""
                val thumbnail = video.thumb_nail ?: ""
                val videoUrl = video.video_url ?: ""

                val videoItem = VideoItem(title, thumbnail, videoUrl)
                workVideos.add(videoItem)
            }
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