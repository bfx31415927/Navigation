package com.example.fullfeaturedhosts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fullfeaturedhosts.screens.HomeScreen
import com.example.fullfeaturedhosts.screens.WelcomeScreen
import com.example.fullfeaturedhosts.ui.theme.FullFeaturedHostsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FullFeaturedHostsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    // Создаём ViewModel ОДИН РАЗ здесь
    val sharedViewModel: SharedDataViewModel = hiltViewModel()

    NavHost(navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) { backStackEntry ->
            // Передаём sharedViewModel в HomeScreen
            HomeScreen(
                viewModel = sharedViewModel,
                onNavigateToWelcome = { params ->
                    sharedViewModel.setParameters(params)
                    navController.navigate(NavRoutes.Welcome.route)
                })
        }

        composable(NavRoutes.Welcome.route) { backStackEntry ->
            // Передаём тот же sharedViewModel в WelcomeScreen
            WelcomeScreen(
                viewModel = sharedViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    FullFeaturedHostsTheme {
        MainScreen()
    }
}
