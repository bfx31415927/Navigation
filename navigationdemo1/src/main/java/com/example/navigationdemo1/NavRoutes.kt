package com.example.navigationdemo1

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object BuiltInTypes : NavRoutes("built_in_types")
    object SerializeTypes : NavRoutes("serialize_types")
    object ParcelableTypes : NavRoutes("parcelable_types")
    object IntList: NavRoutes("int_list")
    object EnumTypes: NavRoutes("enum_types")
}