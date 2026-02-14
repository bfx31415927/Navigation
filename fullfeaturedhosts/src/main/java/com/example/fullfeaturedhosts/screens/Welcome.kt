package com.example.fullfeaturedhosts.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.fullfeaturedhosts.SharedDataViewModel

@Composable
fun WelcomeScreen(
    parameters: Map<String, Any>,
    viewModel: SharedDataViewModel,
    onBack: () -> Unit
) {
    // Сохраняем параметры с учётом поворота экрана
    val localParams by rememberSaveable(parameters.keys.toList()) {
        mutableStateOf(parameters)
    }

    Column {
        Text("Welcome Screen")
        Text("Received params: $localParams")
        Button(onClick = onBack) {
            Text("Back to Home")
        }
    }
}
