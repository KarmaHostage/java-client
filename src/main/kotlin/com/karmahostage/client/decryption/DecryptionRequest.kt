package com.karmahostage.client.encryption

import com.karmahostage.client.KarmahostageRequest

data class DecryptionRequest(val cipherText: String,
                             val keyId: String) : KarmahostageRequest