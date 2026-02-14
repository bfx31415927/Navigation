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
import com.example.fullfeaturedhosts.utils.decodeParams
import com.example.fullfeaturedhosts.utils.encodeParams

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

    NavHost(navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) { backStackEntry ->
            val viewModel: SharedDataViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                onNavigateToWelcome = { params ->
                    navController.navigate(
                        "${NavRoutes.Welcome.route}?params=${encodeParams(params)}"
                    )
                }
            )
        }

        composable(
            route = "${NavRoutes.Welcome.route}?params={params}",
            arguments = listOf(navArgument("params") { type = NavType.StringType })
        ) { backStackEntry ->
            val paramsString = backStackEntry.arguments?.getString("params")
            val parsedParams = decodeParams(paramsString)
            val viewModel: SharedDataViewModel = hiltViewModel()

            // Сохраняем параметры в ViewModel
            viewModel.setParameters(parsedParams)

            WelcomeScreen(
                parameters = parsedParams,
                viewModel = viewModel,
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