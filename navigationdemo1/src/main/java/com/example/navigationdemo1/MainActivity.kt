/*
Этот модуль показывает, как передавать в другой хост:
1) встроенные типы(кнопка "Send to BuiltInTypes"):
-----------------
NavType.StringType: "Alex"
NavType.IntType:    42
NavType.BoolType:   true, false
NavType.FloatType:  3.14f
NavType.LongType    123456789L
NavType.DoubleType  3.14159

⚠️ Обратите внимание: Double и Float требуют аккуратной передачи (особенно в URL).

2. Через сериализацию/десериализацию (кнопка "Send to SerializeTypes"):
3. Parcelable объектs  (кнопка "Send to ParcelableTypes"):
4. список Int-значений  (кнопка "Send to IntList"):
5. enum классы

 Пришлось добавить поддержку аннотаций @Parcelize:
 a) В libs.versions.toml:
    добавил в [libraries]
        kotlinx-parcelize = { module = "org.jetbrains.kotlin:kotlin-parcelize-runtime", version.ref = "kotlin" }
    добавил в [plugins]
        kotlin-parcelize = { id = "org.jetbrains.kotlin.plugin.parcelize", version.ref = "kotlin" }
  б) В build.gradle.kts(:navigationdemo1)
    добавил в plugins {
        id("org.jetbrains.kotlin.plugin.parcelize") // добавьте эту строку
    добавил в dependencies {
        implementation(libs.kotlinx.parcelize)

 Пришлось добавить поддержку сериализации/десериализации:
 a) В libs.versions.toml:
    [versions]
        kotlinxSerializationJson = "1.8.0"
    [libraries]
        kotlinx-serialization-json = { group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref = "kotlinxSerializationJson" }
    [plugins]
        kotlin-plugin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
  б) В build.gradle.kts(:navigationdemo1)
    plugins {
        alias(libs.plugins.kotlin.plugin.serialization)
    dependencies {
        implementation(libs.kotlinx.serialization.json)
*/

package com.example.navigationdemo1

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigationdemo1.screens.BuiltInTypes
import com.example.navigationdemo1.screens.EnumTypes
import com.example.navigationdemo1.screens.Home
import com.example.navigationdemo1.screens.IntList
import com.example.navigationdemo1.screens.ParcelableTypes
import com.example.navigationdemo1.screens.ScreenMode
import com.example.navigationdemo1.screens.SerializeTypes
import com.example.navigationdemo1.screens.User
import com.example.navigationdemo1.screens.User1
import com.example.navigationdemo1.ui.theme.NavigationDemoTheme1
import kotlinx.serialization.json.Json
import java.net.URLDecoder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationDemoTheme1 {
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

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            Home(navController = navController)
        }

        composable(
            route = NavRoutes.BuiltInTypes.route + "/{id}/{name}/{active}/{score}/{timestamp}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("name") { type = NavType.StringType },
                navArgument("active") { type = NavType.BoolType },
                navArgument("score") { type = NavType.FloatType },
                navArgument("timestamp") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val active = backStackEntry.arguments?.getBoolean("active") ?: false
            val score = backStackEntry.arguments?.getFloat("score") ?: 0f
            val timestamp = backStackEntry.arguments?.getLong("timestamp") ?: 0L

            BuiltInTypes(id, name, active, score, timestamp)
        }

        composable(
            route = "${NavRoutes.SerializeTypes.route}?user={user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("user") ?: ""
            val decodedJson = URLDecoder.decode(json, "UTF-8")
            val user = try {
                Json.decodeFromString<User>(decodedJson)
            } catch (e: Exception) {
                User(0, "Unknown")
            }
            SerializeTypes(user = user)
        }

        composable(
            route = NavRoutes.ParcelableTypes.route,
            arguments = listOf(
                navArgument("user1") {
                    type = NavType.ParcelableType(User1::class.java)
                }
            )
        ) { backStackEntry ->
            // Извлекаем Parcelable из аргументов
            val arguments = backStackEntry.arguments
            val user1: User1 = if (Build.VERSION.SDK_INT >= 33) {
                arguments?.getParcelable("user1", User1::class.java) ?: User1(0, "Unknown")
            } else {
                @Suppress("DEPRECATION")
                if (arguments is Bundle) {
                    arguments.getParcelable<User1>("user1") ?: User1(0, "Unknown")
                } else {
                    User1(0, "Unknown")
                }
            }

            ParcelableTypes(user1 = user1)
        }

        composable(
            route = NavRoutes.IntList.route,
            arguments = listOf(navArgument("intList") { type = NavType.IntArrayType })
        ) { backStackEntry ->
            val list = backStackEntry.arguments?.getIntArray("intList")?.toList() ?: emptyList()
            IntList(intList = list)
        }

        composable(
            route = "${NavRoutes.EnumTypes.route}/{mode}",
            arguments = listOf(navArgument("mode") { type = NavType.EnumType(ScreenMode::class.java) })
        ) { backStackEntry ->
            val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                backStackEntry.arguments?.getSerializable("mode", ScreenMode::class.java) ?: ScreenMode.VIEW
            } else {
                @Suppress("DEPRECATION")
                backStackEntry.arguments?.getSerializable("mode") as? ScreenMode ?: ScreenMode.VIEW
            }
            EnumTypes(mode = mode)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    NavigationDemoTheme1 {
        MainScreen()
    }
}