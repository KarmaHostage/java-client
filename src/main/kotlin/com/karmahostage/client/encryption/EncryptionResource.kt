package com.karmahostage.client.encryption

import com.karmahostage.client.key.Key
import com.karmahostage.client.net.APIClient
import com.karmahostage.client.util.ObjectMapping

class EncryptionResource(private val apiClient: APIClient,
                         private val key: Key,
                         private val objectMapping: ObjectMapping) {

    fun encrypt(plaintext: String): EncryptionResponse? {
        return this.apiClient.post("/encrypt",
                objectMapping.transform(EncryptionRequest(plaintext, key.id)))?.let {
            return@let objectMapping.transform(it.response, EncryptionResponse::class.java)
        }
    }
}
