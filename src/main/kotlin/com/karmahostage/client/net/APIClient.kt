package com.karmahostage.client.net

import com.karmahostage.client.KarmahostageConfig
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class APIClient(val config: KarmahostageConfig) {
    fun get(endpoint: String): ApiResponse? {
        return try {
            val request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(constructFullUrl(endpoint)))
                    .header("X-API-KEY", config.apiKey)
                    .build()
            val response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString())
            ApiResponse(response.body())
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }
    }

    fun post(endpoint: String,
             content: String? = null): ApiResponse? {
        return try {
            val request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(content))
                    .uri(URI.create(constructFullUrl(endpoint)))
                    .header("X-API-KEY", config.apiKey)
                    .header("Content-Type", "application/json")
                    .build()
            val response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString())
            ApiResponse(response.body())
        } catch (ex: Exception) {
            null
        }
    }

    private fun constructFullUrl(endpoint: String): String {
        return "${config.endpoint}$endpoint"
    }
}