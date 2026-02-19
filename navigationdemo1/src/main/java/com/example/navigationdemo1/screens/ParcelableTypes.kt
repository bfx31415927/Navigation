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
fun ParcelableTypes(user1: User1) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth()
    ) {
        Text("ParcelableTypes", style = MaterialTheme.typography.headlineSmall)

        Text("user1.id: ${user1.id}")
        Text("user1.name: ${user1.name}")
    }
}

