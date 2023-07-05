package com.example.group401.API
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
class Request {
    private val apiService: VideDataService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://181.215.69.116:7777/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        apiService = retrofit.create(VideDataService::class.java)
    }

    suspend fun getRandomVideos(count: Int): List<VideoData> {
        return apiService.getRandomVideos(count)
    }
}