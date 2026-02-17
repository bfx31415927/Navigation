package com.example.fullfeaturedhosts.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fullfeaturedhosts.SharedDataViewModel
import com.example.fullfeaturedhosts.NavigationParameters

@Composable
fun WelcomeScreen(
    viewModel: SharedDataViewModel,  // Получаем тот же экземпляр
    onBack: () -> Unit
) {
    val currentParams = viewModel.parameters.value  // Читаем из единого источника

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(30.dp)
            .fillMaxWidth()
    ) {
        Text("Welcome Screen")
        currentParams?.let { params ->
            Text("Current params: $params")
        } ?: Text("No parameters available")
        Button(onClick = onBack) {
            Text("Back to Home")
        }
    }
}
