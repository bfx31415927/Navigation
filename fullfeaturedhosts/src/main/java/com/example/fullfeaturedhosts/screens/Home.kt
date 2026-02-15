package com.example.fullfeaturedhosts.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fullfeaturedhosts.SharedDataViewModel
import com.example.fullfeaturedhosts.NavigationParameters

@Composable
fun HomeScreen(
    viewModel: SharedDataViewModel,  // Получаем извне, не создаём заново
    onNavigateToWelcome: (NavigationParameters) -> Unit
) {
    val savedParams = viewModel.parameters.value

    Column {
        Text("Home Screen")
        if (savedParams != null) {
            Text("Received params: $savedParams")
        } else {
            Text("No parameters available")
        }
        Button(onClick = {
            val params = NavigationParameters(key1 = "value1", key2 = 42)
            viewModel.setParameters(params)  // Сохраняем в единый экземпляр
            onNavigateToWelcome(params)
        }) {
            Text("Go to Welcome")
        }
    }
}