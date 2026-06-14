package com.corryn.composenavigationplayground

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

enum class RootDestinations(
    val label: String,
    val icon: Int,
    val destination: AppDestination
) {
    HOME("Home", R.drawable.ic_home, AppDestination.Home),
    FAVORITES("Favorites", R.drawable.ic_favorite, AppDestination.Favorites),
    PROFILE("Profile", R.drawable.ic_account_box, AppDestination.Profile),
}

sealed interface AppDestination {

    @Serializable
    data class Home(
        val name: String
    ): AppDestination

    @Serializable
    data object Favorites: AppDestination

    @Serializable
    data object Profile: AppDestination

    @Serializable
    data class Details(
        val id: String
    ): AppDestination

}
