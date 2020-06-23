package com.karmahostage.client.key

import com.karmahostage.client.net.APIClient
import com.karmahostage.client.util.ObjectMapping
import com.karmahostage.key.request.CreateKeyRequest
import com.karmahostage.key.response.KeyCreatedResponse

class KarmahostageKeyResource(val apiClient: APIClient,
                              val objectMapping: ObjectMapping) {

    fun create(name: String): KeyCreatedResponse? {
        return apiClient.post(
                "/keys",
                objectMapping.transform(CreateKeyRequest(
                        name = name
                ))
        )?.let {
            return@let objectMapping.transform(it.response, KeyCreatedResponse::class.java)
        }
    }

    fun create(createKeyCommand: CreateKeyRequest): KeyCreatedResponse? {
        return apiClient.post(
                "/keys",
                objectMapping.transform(createKeyCommand)
        )?.let {
            return@let objectMapping.transform(it.response, KeyCreatedResponse::class.java)
        }
    }

    fun retrieve(keyId: String): Key? {
        return apiClient.get("/keys/$keyId")?.let {
            return objectMapping.transform(
                    it.response,
                    Key::class.java
            ).also { key ->
                key.resources(objectMapping, apiClient)
            }
        }
    }

    fun list(): List<Key> {
        return apiClient.get("/keys")?.response?.let {
            objectMapping.transformList(it, Key::class.java).map { key ->
                key.resources(objectMapping, apiClient)
                key
            }
        } ?: emptyList()
    }
}