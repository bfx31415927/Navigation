package com.example.fullfeaturedhosts.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.fullfeaturedhosts.SharedDataViewModel

@Composable
fun HomeScreen(
    viewModel: SharedDataViewModel,
    onNavigateToWelcome: (Map<String, Any>) -> Unit
) {
    // Сохраняем параметры из ViewModel с учётом поворота экрана
    val savedParams by rememberSaveable {
        derivedStateOf { viewModel.parameters.value }
    }

    Column {
        Text("Home Screen")
        savedParams?.let { params ->
            Text("Received params: $params")
        }
        Button(onClick = {
            val params = mapOf(
                "key1" to "value1",
                "key2" to 42
            )
            onNavigateToWelcome(params)
        }) {
            Text("Go to Welcome")
        }
    }
}

