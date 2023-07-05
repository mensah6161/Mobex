package com.example.group401.API

import retrofit2.http.GET
import retrofit2.http.Path

interface VideoDataService {
    @GET("video/random/{count}")
    suspend fun getRandomVideos(@Path("count") count: Int): List<VideoData>
}