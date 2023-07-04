package com.example.group401

class VideoListGenerator {
    companion object {
        fun getCategories(): List<Category> {
            // Generate and return the list of categories dynamically here
            val categories = mutableListOf<Category>()

            // Add categories and their video items
            val sealifeVideos = listOf(
                VideoItem("Video First", R.drawable.thumbfam1, "https://test-videos.co.uk/vids/jellyfish/mp4/h264/1080/Jellyfish_1080_10s_1MB.mp4"),
                VideoItem("Video 2", R.drawable.thumbfam2, "DEEP_LINK_2"),
                VideoItem("Video 3", R.drawable.thumbfam3, "DEEP_LINK_3"),
                VideoItem("Video 2", R.drawable.thumbfam4, "DEEP_LINK_4"),
                VideoItem("Video 3", R.drawable.thumbfam5, "DEEP_LINK_5"),
                VideoItem("Video 2", R.drawable.thumbfam6, "DEEP_LINK_6"),
                VideoItem("Video 3", R.drawable.thumbfam7, "DEEP_LINK_7"),
                VideoItem("Video 2", R.drawable.thumbfam8, "DEEP_LINK_8"),
                VideoItem("Video 3", R.drawable.thumbfam9, "DEEP_LINK_9")
            )
            val workVideos = listOf(
                VideoItem("Video A", R.drawable.thumbwork1, "DEEP_LINK_A"),
                VideoItem("Video B", R.drawable.thumbwork2, "DEEP_LINK_B"),
                VideoItem("Video C", R.drawable.thumbwork3, "DEEP_LINK_C"),
                VideoItem("Video D", R.drawable.thumbwork4, "DEEP_LINK_D"),
                VideoItem("Video E", R.drawable.thumbwork5, "DEEP_LINK_E"),
                VideoItem("Video F", R.drawable.thumbwork1, "DEEP_LINK_D"),
                VideoItem("Video G", R.drawable.thumbwork3, "DEEP_LINK_D"),
                VideoItem("Video H", R.drawable.thumbwork2, "DEEP_LINK_F"),
                VideoItem("Video I", R.drawable.thumbwork4, "DEEP_LINK_G")
            )
            val familyVideos = listOf(
                VideoItem("Video A", R.drawable.thumbfam11, "DEEP_LINK_A"),
                VideoItem("Video B", R.drawable.thumbfam12, "DEEP_LINK_B"),
                VideoItem("Video C", R.drawable.thumbfam13, "DEEP_LINK_C"),
                VideoItem("Video D", R.drawable.thumbfam14, "DEEP_LINK_D"),
                VideoItem("Video E", R.drawable.thumbfam15, "DEEP_LINK_E"),
                VideoItem("Video F", R.drawable.thumbfam11, "DEEP_LINK_F"),
                VideoItem("Video G", R.drawable.thumbfam12, "DEEP_LINK_G"),
                VideoItem("Video H", R.drawable.thumbfam13, "DEEP_LINK_H"),
                VideoItem("Video I", R.drawable.thumbfam14, "DEEP_LINK_I"),
                VideoItem("Video J", R.drawable.thumbfam15, "DEEP_LINK_J")
            )

            // Add categories to the list
            categories.add(Category("Sealife", sealifeVideos))
            categories.add(Category("Work", workVideos))
            categories.add(Category("Family", familyVideos))

            return categories
        }
    }
}