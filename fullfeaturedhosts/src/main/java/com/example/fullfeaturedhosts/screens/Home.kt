package com.example.fullfeaturedhosts.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fullfeaturedhosts.SharedDataViewModel
import com.example.fullfeaturedhosts.NavigationParameters

@Composable
fun HomeScreen(
    viewModel: SharedDataViewModel,
    onNavigateToWelcome: () -> Unit
) {
    Log.d("Lifecycle1", "HomeScreen START")

    // Шаг 1. Читаем начальные значения ОДИН РАЗ при создании компонента
    val initialParams = remember {
        viewModel.parameters.value
    }

    // Шаг 2. Создаём локальные состояния БЕЗ подписки на ViewModel
    val userName = remember { mutableStateOf(initialParams?.key1 ?: "") }
    val userId = remember { mutableStateOf(initialParams?.key2?.toString() ?: "") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(30.dp)
            .fillMaxWidth()
    ) {
        Text("Home Screen")

        CustomTextField(
            title = "Enter your name",
            textState = userName.value,
            onTextChange = { userName.value = it },  // Только локальное обновление
            keyboardType = KeyboardType.Text,
            width = 160.dp
        )

        Spacer(modifier = Modifier.size(30.dp))

        CustomTextField(
            title = "Enter your ID",
            textState = userId.value,
            onTextChange = { userId.value = it },
            keyboardType = KeyboardType.Number,
            width = 160.dp
        )
        Log.d("Lifecycle1", "CustomTextField")

        Spacer(modifier = Modifier.size(30.dp))

        Button(onClick = {
            // Синхронизируем с ViewModel только при нажатии кнопки
            viewModel.setParameters(
                NavigationParameters(
                    key1 = userName.value,
                    key2 = userId.value.ifBlank { "0" }.toInt()
                )
            )
            onNavigateToWelcome()
        }) {
            Text("Go to Welcome")
        }
    }
}
@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType,
    width: Dp
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        label = { Text(title) },
        modifier = Modifier
            .padding(1.dp)
            .width(width)
            .height(60.dp)
            .background(Color.White),

        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Left,
        )
    )
}
