package com.example.androidalarm.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException

class JsonFactory<T> {
    @Throws(IOException::class)
    fun objectToJson(`object`: T): String {
        val mapper = ObjectMapper()
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false)
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        return mapper.writeValueAsString(`object`)
    }
}