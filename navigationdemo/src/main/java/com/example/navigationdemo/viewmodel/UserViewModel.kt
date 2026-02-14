package com.example.navigationdemo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel : ViewModel() {
    private val _userName = MutableStateFlow<String>("")
    val userName: StateFlow<String> = _userName

    init {
        Log.d("UserViewModel", "ViewModel created")
    }

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun clearUserName() {
        _userName.value = ""
    }
}
