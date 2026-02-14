package com.example.navigationdemo.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.navigationdemo.NavRoutes

@Composable
fun Welcome(navController: NavHostController, userName: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Welcome, $userName!", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = {
                navController.navigate(NavRoutes.Profile.route) {
                    //если надо по BACK попасть в предыдущий экран (на Home)
                    //popUpTo(NavRoutes.Home.route)
                    //если надо по BACK попасть в стартовый экран (в данном случае на Home)
                    popUpTo(navController.graph.findStartDestination().id){
                       inclusive = true//означает, что из стека удалится и стартовый экран тоже (Home в данном случае)
                                        // (по умолчанию false, при котором стартовый экран остается в стеке)
                    }
                }
            }) {
                Text(text = "Set up your Profile")
            }
        }
    }
}
