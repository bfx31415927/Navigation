package com.example.fullfeaturedhosts.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fullfeaturedhosts.SharedDataViewModel
import com.example.fullfeaturedhosts.NavigationParameters

@Composable
fun WelcomeScreen(
    viewModel: SharedDataViewModel,  // Получаем тот же экземпляр
    onBack: () -> Unit
) {
    val currentParams = viewModel.parameters.value  // Читаем из единого источника

    Column {
        Text("Welcome Screen")
        currentParams?.let { params ->
            Text("Current params: $params")  // Всегда актуальные данные
        } ?: Text("No parameters available")
        Button(onClick = onBack) {
            Text("Back to Home")
        }
    }
}
