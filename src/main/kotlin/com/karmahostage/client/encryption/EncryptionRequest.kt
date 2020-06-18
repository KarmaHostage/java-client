package com.karmahostage.client.encryption

import com.karmahostage.client.KarmahostageRequest

data class EncryptionRequest(val plainText: String,
                             val keyId: String) : KarmahostageRequest