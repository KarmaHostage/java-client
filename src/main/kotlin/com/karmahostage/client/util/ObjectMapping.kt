package com.karmahostage.client.util

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.karmahostage.client.KarmahostageRequest

open class ObjectMapping {

    val objectmapper: ObjectMapper = ObjectMapper()

    init {
        objectmapper.registerModule(KotlinModule())
        objectmapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
    }

    fun <T> transform(string: String, clazz: Class<T>): T {
        return objectmapper.readValue(string, clazz)
    }

    fun transform(obj: Any): String {
        return objectmapper.writeValueAsString(obj)
    }

    fun <T> transformList(string: String, clazz: Class<T>): List<T> {
        return objectmapper.readValue(string, objectmapper.typeFactory.constructCollectionType(List::class.java, clazz))
    }
}

