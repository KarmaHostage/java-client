package com.karmahostage.client.decryption

import com.karmahostage.client.encryption.DecryptionRequest
import com.karmahostage.client.key.Key
import com.karmahostage.client.net.APIClient
import com.karmahostage.client.util.ObjectMapping

class DecryptionResource(private val apiClient: APIClient,
                         private val key: Key,
                         private val objectMapping: ObjectMapping) {

    fun decrypt(plaintext: String): DecryptionResponse? {
        return this.apiClient.post("/decrypt",
                objectMapping.transform(DecryptionRequest(plaintext, key.id)))?.let {
            return@let objectMapping.transform(it.response, DecryptionResponse::class.java)
        }
    }
}
