package com.karmahostage.client.key

import com.karmahostage.client.net.APIClient
import com.karmahostage.client.util.ObjectMapping

class KeyManagementResource(val apiClient: APIClient,
                            val key: Key,
                            val objectMapping: ObjectMapping) {
    fun rotate() {
        this.apiClient.post(endpoint = "/keys/${key.id}/rotate")
    }
}