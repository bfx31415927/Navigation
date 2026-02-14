package com.example.fullfeaturedhosts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedDataViewModel : ViewModel() {
    private val _parameters = mutableStateOf<Map<String, Any>?>(null)
    val parameters: State<Map<String, Any>?> = _parameters

    fun setParameters(newParams: Map<String, Any>) {
        _parameters.value = newParams
    }

    // Опционально: функция для сброса параметров
    fun clearParameters() {
        _parameters.value = null
    }
}