package com.example.group401.API
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory



@JsonClass(generateAdapter = true)
data class VideoData(
    @Json(name = "title") val title: String?,
    @Json(name = "thumb_nail") val thumb_nail: String?,
    @Json(name = "video_url") val video_url: String?
)
class APIClientRequest {
    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://181.215.69.116:7777/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val videoService: VideoDataService = retrofit.create(VideoDataService::class.java)



}