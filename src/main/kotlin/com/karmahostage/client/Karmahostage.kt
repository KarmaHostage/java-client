package com.karmahostage.client

import com.karmahostage.client.key.KarmahostageKeyResource
import com.karmahostage.client.net.APIClient
import com.karmahostage.client.secret.KarmahostageSecretResource
import com.karmahostage.client.util.ObjectMapping

class Karmahostage(apiKey: String, endpoint: String) {

    private val apiClient = APIClient(
            KarmahostageConfig(apiKey = apiKey, endpoint = endpoint)
    )

    fun keys(): KarmahostageKeyResource {
        return KarmahostageKeyResource(apiClient, ObjectMapping())
    }

    fun secrets(): KarmahostageSecretResource {
        return KarmahostageSecretResource(apiClient, ObjectMapping())
    }

    class Builder {
        var apiKey: String? = null
        var endpoint: String = "https://api.karmahostage.com"

        fun apikey(apikey: String): Builder {
            this.apiKey = apikey
            return this
        }

        fun endpoint(endpoint: String): Builder {
            this.endpoint = endpoint
            return this
        }

        fun build(): Karmahostage {
            return Karmahostage(apiKey!!, endpoint)
        }
    }

    companion object Factory {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }
}