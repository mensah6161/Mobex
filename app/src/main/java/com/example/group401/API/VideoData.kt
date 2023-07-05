package com.example.group401.API

data class VideoData(
    val site_url: String?,
    val video_url: String?,
    val video_size: Int?,
    val thumb_nail: String?,
    val created: String?,
    val institution: String?,
    val institution_logo: String?,
    val publisher: String?,
    val title: String?,
    val keywords: List<String>?,
    val duration: Int?,
    val category: String?,
    val available_from: String?,
    val available_to: String?,
    val is_child_friendly: Int?,
    val child_friendly: Int?
)
