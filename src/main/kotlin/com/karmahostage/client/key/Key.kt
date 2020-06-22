package com.karmahostage.client.key

import com.fasterxml.jackson.annotation.JsonFormat
import com.karmahostage.client.decryption.DecryptionResource
import com.karmahostage.client.decryption.DecryptionResponse
import com.karmahostage.client.encryption.EncryptionResource
import com.karmahostage.client.encryption.EncryptionResponse
import com.karmahostage.client.net.APIClient
import com.karmahostage.client.sign.SignatureResource
import com.karmahostage.client.sign.SignatureResponse
import com.karmahostage.client.util.ObjectMapping
import java.util.*

data class Key(
        val id: String,
        val name: String,
        val description: String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        val creationDate: Date,
        val latestVersion: Int,
        val minimumDecryptionVersion: Int,
        val minimumEncryptionVersion: Int,
        val type: String,
        val exportable: Boolean,
        val deletable: Boolean,
        val derived: Boolean
) {

    private lateinit var encryptionResource: EncryptionResource
    private lateinit var decryptionResource: DecryptionResource
    private lateinit var keyManagementResource: KeyManagementResource
    private lateinit var signatureResource: SignatureResource

    fun resources(objectMapping: ObjectMapping,
                  apiClient: APIClient) {
        this.encryptionResource = EncryptionResource(apiClient, this, objectMapping)
        this.decryptionResource = DecryptionResource(apiClient, this, objectMapping)
        this.keyManagementResource = KeyManagementResource(apiClient, this, objectMapping)
        this.signatureResource = SignatureResource(apiClient, this, objectMapping)
    }

    fun encrypt(plainText: String): EncryptionResponse? {
        return this.encryptionResource.encrypt(plainText)
    }

    fun decrypt(cipherText: String): DecryptionResponse? {
        return this.decryptionResource.decrypt(cipherText)
    }

    fun sign(plainText: String): SignatureResponse? {
        return this.signatureResource.sign(plainText)
    }

    fun rotate() {
        this.keyManagementResource.rotate()
    }
}