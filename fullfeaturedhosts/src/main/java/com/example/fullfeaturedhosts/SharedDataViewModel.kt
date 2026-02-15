package com.example.fullfeaturedhosts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

@Serializable
data class NavigationParameters(
    val key1: String = "",
    val key2: Int = 0
)

class SharedDataViewModel : ViewModel() {
    private val _parameters = mutableStateOf<NavigationParameters?>(null)
    val parameters: State<NavigationParameters?> = _parameters

    fun setParameters(newParams: NavigationParameters) {
        _parameters.value = newParams
    }

    fun encodeParameters(): String {
        return _parameters.value?.let { params ->
            URLEncoder.encode(Json.encodeToString(params), "UTF-8")
        } ?: ""
    }

    fun decodeParameters(encodedParams: String?): NavigationParameters? {
        return if (encodedParams != null && encodedParams.isNotEmpty() && encodedParams != "null") {
            try {
                Json.decodeFromString<NavigationParameters>(
                    URLDecoder.decode(encodedParams, "UTF-8")
                )
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }

    fun clearParameters() {
        _parameters.value = null
    }
}
