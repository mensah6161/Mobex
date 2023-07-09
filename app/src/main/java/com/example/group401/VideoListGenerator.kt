package com.example.group401

import com.example.group401.API.VideoAPI

class VideoListGenerator {
    companion object {
        const val Category1 = "Show/Magazin"
        const val Category2 = "Film"
        const val Category3 = "Dokumentation/Reportage"
        const val Category4 = "Nachrichten/Information"
        const val Category5 = "Serie"
        fun getCategories(): List<Category> {
            // Generate and return the list of categories dynamically here
            val categories = mutableListOf<Category>()


            // Add categories and their video items
            val videoApi = VideoAPI()
            val videoItemListbyCategory1 = videoApi.fetchRandomVideoDataOfCategory(5, Category1)
            if (videoItemListbyCategory1 != null) {
                val videoItemsOfCategory1 = videoItemListbyCategory1.map { videoItem ->
                    VideoItemKotlin(videoItem.title,
                        videoItem.thumb_nail,
                        videoItem.video_url,
                        videoItem.site_url,
                        videoItem.video_size,
                        videoItem.created,
                        videoItem.institution,
                        videoItem.institution_logo,
                        videoItem.publisher,
                        videoItem.duration,
                        videoItem.category,
                        videoItem.subcategory,
                        videoItem.available_from,
                        videoItem.available_to,
                        videoItem.is_child_friendly,
                        videoItem.child_friendly)
                }
                categories.add(Category(Category1, videoItemsOfCategory1))
            }
            val videoItemListbyCategory2 = videoApi.fetchRandomVideoDataOfCategory(5, Category2)
            if (videoItemListbyCategory2 != null) {
                val videoItemsOfCategory2 = videoItemListbyCategory2.map { videoItem ->
                    VideoItemKotlin(videoItem.title,
                        videoItem.thumb_nail,
                        videoItem.video_url,
                        videoItem.site_url,
                        videoItem.video_size,
                        videoItem.created,
                        videoItem.institution,
                        videoItem.institution_logo,
                        videoItem.publisher,
                        videoItem.duration,
                        videoItem.category,
                        videoItem.subcategory,
                        videoItem.available_from,
                        videoItem.available_to,
                        videoItem.is_child_friendly,
                        videoItem.child_friendly)                }
                categories.add(Category(Category2, videoItemsOfCategory2))
            }
            val videoItemListbyCategory3 = videoApi.fetchRandomVideoDataOfCategory(5, Category3)
            if (videoItemListbyCategory3 != null) {
                val videoItemsOfCategory3 = videoItemListbyCategory3.map { videoItem ->
                    VideoItemKotlin(videoItem.title,
                        videoItem.thumb_nail,
                        videoItem.video_url,
                        videoItem.site_url,
                        videoItem.video_size,
                        videoItem.created,
                        videoItem.institution,
                        videoItem.institution_logo,
                        videoItem.publisher,
                        videoItem.duration,
                        videoItem.category,
                        videoItem.subcategory,
                        videoItem.available_from,
                        videoItem.available_to,
                        videoItem.is_child_friendly,
                        videoItem.child_friendly)                }
                categories.add(Category(Category3, videoItemsOfCategory3))
            }
            val videoItemListbyCategory4 = videoApi.fetchRandomVideoDataOfCategory(5, Category4)
            if (videoItemListbyCategory4 != null) {
                val videoItemsOfCategory4 = videoItemListbyCategory4.map { videoItem ->
                    VideoItemKotlin(
                        videoItem.title,
                        videoItem.thumb_nail,
                        videoItem.video_url,
                        videoItem.site_url,
                        videoItem.video_size,
                        videoItem.created,
                        videoItem.institution,
                        videoItem.institution_logo,
                        videoItem.publisher,
                        videoItem.duration,
                        videoItem.category,
                        videoItem.subcategory,
                        videoItem.available_from,
                        videoItem.available_to,
                        videoItem.is_child_friendly,
                        videoItem.child_friendly
                    )
                }
                    categories.add(Category(Category4, videoItemsOfCategory4))
            }
            val videoItemListbyCategory5 = videoApi.fetchRandomVideoDataOfCategory(5, Category5)
            if (videoItemListbyCategory5 != null) {
                val videoItemsOfCategory5 = videoItemListbyCategory5.map { videoItem ->
                    VideoItemKotlin(
                        videoItem.title,
                        videoItem.thumb_nail,
                        videoItem.video_url,
                        videoItem.site_url,
                        videoItem.video_size,
                        videoItem.created,
                        videoItem.institution,
                        videoItem.institution_logo,
                        videoItem.publisher,
                        videoItem.duration,
                        videoItem.category,
                        videoItem.subcategory,
                        videoItem.available_from,
                        videoItem.available_to,
                        videoItem.is_child_friendly,
                        videoItem.child_friendly)
                }
                categories.add(Category(Category5, videoItemsOfCategory5))
            }
            return categories
        }

    }
}