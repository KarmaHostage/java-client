package com.karmahostage.client.net

import com.karmahostage.client.KarmahostageConfig
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class APIClient(val config: KarmahostageConfig) {
    private val client: OkHttpClient = OkHttpClient()

    fun get(endpoint: String): ApiResponse? {
        return try {
            val request = Request.Builder()
                    .addHeader("X-API-KEY", config.apiKey)
                    .url(constructFullUrl(endpoint))
                    .build()
            val execute = client.newCall(
                    request
            ).execute()
            ApiResponse(execute.body!!.string())
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }
    }

    fun post(endpoint: String,
             content: String? = null): ApiResponse? {
        return try {
            val request = Request.Builder()
                    .addHeader("X-API-KEY", config.apiKey)
                    .url(constructFullUrl(endpoint))
                    .post(content?.toRequestBody("application/json".toMediaType()) ?: "".toRequestBody())
                    .build()
            val execute = client.newCall(request).execute()
            ApiResponse(execute.body!!.string())
        } catch (ex: Exception) {
            null
        }
    }

    private fun constructFullUrl(endpoint: String): String {
        return "${config.endpoint}$endpoint"
    }
}