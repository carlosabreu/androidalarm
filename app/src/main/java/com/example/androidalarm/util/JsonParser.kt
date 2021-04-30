package com.example.androidalarm.util

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException

class JsonParser<T> {
    @Throws(IOException::class)
    fun parse(jSon: String?, classe: Class<T>?): T {
        return ObjectMapper().readValue(jSon, classe)
    }
}