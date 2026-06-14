package com.corryn.composenavigationplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.corryn.composenavigationplayground.ui.theme.ComposeNavigationPlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val useNavigation3 = rememberSaveable {
                true
            }

            ComposeNavigationPlaygroundTheme {
                if (useNavigation3) {
                    Navigation3SuiteScaffoldPlaygroundApp()
                } else {
                    ComposeNavigationPlaygroundApp()
                }
            }
        }
    }
}

@Composable
fun ComposeNavigationPlaygroundApp() {
    var userName by rememberSaveable { mutableStateOf("Android") }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestination.Home(name = userName)
    ) {
        composable<AppDestination.Home> {
            val route = it.toRoute<AppDestination.Home>()
            HomeScreen(route.name)
        }
        composable<AppDestination.Details> {
            val route = it.toRoute<AppDestination.Details>()
            DetailsScreen(id = route.id)
        }
    }
}

@PreviewScreenSizes
@Composable
fun Navigation3SuiteScaffoldPlaygroundApp() {
    var userName by rememberSaveable { mutableStateOf("Android") }

    val appBackstack = rememberSaveable {
        mutableStateListOf<AppDestination>(AppDestination.Home(name = userName))
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            RootDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it.destination == appBackstack.lastOrNull(),
                    onClick = {
                        appBackstack.apply {
                            clear()
                            add(it.destination)
                        }
                    }
                )
            }
        }
    ) {
        NavDisplay(
                backStack = appBackstack,
                onBack = { appBackstack.removeLastOrNull() },
                entryProvider = { key ->
                    when (key) {
                        is AppDestination.Home -> NavEntry(key) {
                            HomeScreen(name = key.name)
                        }

                        is AppDestination.Favorites -> NavEntry(key) {
                            FavoritesScreen()
                        }

                        is AppDestination.Profile -> NavEntry(key) {
                            ProfileScreen()
                        }

                        is AppDestination.Details -> NavEntry(key) {
                            DetailsScreen(id = key.id)
                        }

                        else -> throw RuntimeException("Unsupported AppDestination: $key")
                    }
                }
            )
    }
}

@Composable
fun HomeScreen(
    name: String,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize()) { padding ->
        Text(
            text = "Hello $name!",
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ComposeNavigationPlaygroundTheme {
        HomeScreen("Android")
    }
}

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { padding ->
        Text(
            text = "Favorites",
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen()
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { padding ->
        Text(
            text = "Profile",
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

@Composable
fun DetailsScreen(
    id: String,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize()) { padding ->
        Text(
            text = "Details for $id",
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(id = "11111")
}