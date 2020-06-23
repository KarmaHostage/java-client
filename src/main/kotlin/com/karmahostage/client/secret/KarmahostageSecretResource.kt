package com.karmahostage.client.secret

import com.karmahostage.client.net.APIClient
import com.karmahostage.client.util.ObjectMapping
import com.karmahostage.secret.request.CreateSecretRequest
import com.karmahostage.secret.response.ExposedSecretResponse
import com.karmahostage.secret.response.SecretCreatedResponse

class KarmahostageSecretResource(val apiClient: APIClient,
                                 val objectMapping: ObjectMapping) {

    fun create(createSecretRequest: CreateSecretRequest): SecretCreatedResponse? {
        return apiClient.post(
                "/secrets",
                objectMapping.transform(createSecretRequest)
        )?.let {
            return@let objectMapping.transform(it.response, SecretCreatedResponse::class.java)
        }
    }

    fun retrieveByKey(key: String): ExposedSecretResponse? {
        return apiClient.get("/secret?path=$key")?.let {
            return objectMapping.transform(
                    it.response,
                    ExposedSecretResponse::class.java
            )
        }
    }


    fun retrieveById(id: String): ExposedSecretResponse? {
        return apiClient.get("/secrets/$id")?.let {
            return objectMapping.transform(
                    it.response,
                    ExposedSecretResponse::class.java
            )
        }
    }

}