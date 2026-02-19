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
fun BuiltInTypes(id: Int, name: String, active: Boolean,
            score: Float, timeStamp: Long) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 50.dp)
            .fillMaxWidth()
    ) {
        Text("BuiltInTypes", style = MaterialTheme.typography.headlineSmall)

        Text("id: $id")
        Text("name: $name")
        Text("active: $active")
        Text("score: $score")
        Text("timeStamp: $timeStamp")
    }
}
