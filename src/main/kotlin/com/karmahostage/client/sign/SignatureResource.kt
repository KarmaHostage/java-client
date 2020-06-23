package com.karmahostage.client.sign

import com.karmahostage.client.key.Key
import com.karmahostage.client.net.APIClient
import com.karmahostage.client.util.ObjectMapping
import com.karmahostage.signature.request.CreateSignatureRequest
import com.karmahostage.signature.response.SignatureResponse

class SignatureResource(private val apiClient: APIClient,
                        private val key: Key,
                        private val objectMapping: ObjectMapping) {

    fun sign(plaintext: String): SignatureResponse? {
        return this.apiClient.post("/sign",
                objectMapping.transform(
                        CreateSignatureRequest(
                                plainText = plaintext,
                                keyId = key.id
                        )
                ))?.let {
            return@let objectMapping.transform(it.response, SignatureResponse::class.java)
        }
    }
}