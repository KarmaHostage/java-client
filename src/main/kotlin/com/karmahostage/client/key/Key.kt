package com.karmahostage.client.key

import com.fasterxml.jackson.annotation.JsonFormat
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
)