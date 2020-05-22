package com.karmahostage.client.key

import com.karmahostage.client.net.APIClient
import com.karmahostage.client.util.ObjectMapping

class KarmahostageKeyResource(val apiClient: APIClient,
                              val objectMapping: ObjectMapping) {

    fun retrieve(): Key? {
        return apiClient.get("/keys")?.let {
            return objectMapping.transform(
                    it.response,
                    Key::class.java
            )
        }
    }

    fun list(): List<Key> {
        return apiClient.get("/keys")?.response?.let { objectMapping.transformList(it, Key::class.java) } ?: emptyList()
    }
}