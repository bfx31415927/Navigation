package com.example.navigationdemo1.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SerializeTypes(user: User) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth()
    ) {
        Text("SerializeTypes", style = MaterialTheme.typography.headlineSmall)

        Text("user.id: ${user.id}")
        Text("user.name: ${user.name}")
    }
}

