package com.example.group401.API
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

class VideoAPI {
    private val gson = Gson()

    fun fetchRandomVideoData(count: Int): List<VideoItem> {
        val apiUrl = "http://181.215.69.116:7777/video/random/?number=$count"
        // Send HTTP GET request and read the response
        val url = URL(apiUrl)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            reader.close()

            try {
                // Convert JSON string to VideoItemList object using Gson
                return gson.fromJson(response.toString(), object : TypeToken<List<VideoItem>>() {}.type)
            } catch (e: JsonSyntaxException) {
                throw Exception("Failed to parse JSON data. Error: ${e.message}")
            }
        } else {
            throw Exception("Failed to fetch video data. Response code: $responseCode")
        }
    }
}
