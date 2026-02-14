package com.example.fullfeaturedhosts.utils

import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

fun encodeParams(params: Map<String, Any>): String {
    return URLEncoder.encode(
        Json.encodeToString(params),
        "UTF-8"
    )
}

fun decodeParams(encodedParams: String?): Map<String, Any> {
    return if (encodedParams != null) {
        try {
            Json.decodeFromString(
                URLDecoder.decode(encodedParams, "UTF-8")
            )
        } catch (e: Exception) {
            emptyMap()
        }
    } else {
        emptyMap()
    }
}
