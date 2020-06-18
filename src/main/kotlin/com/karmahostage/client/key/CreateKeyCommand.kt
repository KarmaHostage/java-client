package com.karmahostage.client.key

data class CreateKeyCommand(val name: String? = null,
                            val keyType: KeyType = KeyType.AES128_GCM96
)