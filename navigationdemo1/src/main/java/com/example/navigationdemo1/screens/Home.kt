package com.example.navigationdemo1.screens

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavHostController
import com.example.navigationdemo1.NavRoutes
//import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URLEncoder


//@Parcelize
@Serializable
data class User(val id: Int, val name: String)// : Parcelable

@Parcelize
data class User1(val id: Int, val name: String) : Parcelable

@Parcelize
data class User2(private val id: Int, private val name: String) : Parcelable

enum class ScreenMode { EDIT, VIEW, CREATE }

@Composable
fun Home(navController: NavHostController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth()
    ) {
        Button(onClick = {
            val id: Int = 42
            val name: String = "Alex"
            val active: Boolean = true
            val score: Float = 3.14f
            val timestamp: Long = 123456789L
            navController.navigate(NavRoutes.BuiltInTypes.route + "/$id/$name/$active/$score/$timestamp")
        }) {
            Text(text = "Send to BuiltInTypes")
        }

        Button(onClick = {
            val user = User(id = 123, name = "Alex")
            val json = Json.encodeToString(user)
            val encodedJson = URLEncoder.encode(json, "UTF-8")
            navController.navigate("${NavRoutes.SerializeTypes.route}?user=$encodedJson")
        }) {
            Text(text = "Send to SerializeTypes")
        }

        Button(onClick = {
            val user1 = User1(id = 123, name = "Alex")
            val bundle = bundleOf("user1" to user1)

            // Ищем destination по маршруту
            val destination = navController.graph.findNode(NavRoutes.ParcelableTypes.route)
                ?: throw IllegalArgumentException("Destination ${NavRoutes.ParcelableTypes.route} not found")

            // Передаем ID destination и Bundle с Parcelable
            navController.navigate(destination.id, bundle)
        }) {
            Text(text = "Send to ParcelableTypes")
        }

        Button(onClick = {
            val intList = listOf(1, 2, 3, 4, 5)
            val bundle = bundleOf("intList" to intList.toIntArray())

            val destination = navController.graph.findNode(NavRoutes.IntList.route)
                ?: throw IllegalArgumentException("Destination ${NavRoutes.IntList.route} not found")

            navController.navigate(destination.id, bundle)
        }) {
            Text("Send to IntList")
        }

        Button(onClick = {
            val mode = ScreenMode.EDIT
            navController.navigate("${NavRoutes.EnumTypes.route}/${mode.name}")
        }) {
            Text("Send to EnumTypes")
        }
    }
}
