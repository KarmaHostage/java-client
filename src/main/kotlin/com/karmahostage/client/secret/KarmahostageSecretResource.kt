package com.karmahostage.client.secret

import com.karmahostage.client.net.APIClient
import com.karmahostage.client.util.ObjectMapping

class KarmahostageSecretResource(val apiClient: APIClient,
                                 val objectMapping: ObjectMapping) {

    fun create(createSecretCommand: CreateSecretCommand): SecretCreatedResponse? {
        return apiClient.post(
                "/secrets",
                objectMapping.transform(createSecretCommand)
        )?.let {
            return@let objectMapping.transform(it.response, SecretCreatedResponse::class.java)
        }
    }

    fun retrieveByKey(key: String): Secret? {
        return apiClient.get("/secret?path=$key")?.let {
            return objectMapping.transform(
                    it.response,
                    Secret::class.java
            )
        }
    }


    fun retrieveById(id: String): Secret? {
        return apiClient.get("/secrets/$id")?.let {
            return objectMapping.transform(
                    it.response,
                    Secret::class.java
            )
        }
    }

}