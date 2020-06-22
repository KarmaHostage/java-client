package com.karmahostage.client.sign

import com.karmahostage.client.KarmahostageRequest

data class SignRequest(val plainText: String,
                       val keyId: String) : KarmahostageRequest