package com.corryn.composenavigationplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
            ComposeNavigationPlaygroundTheme {
                ComposeNavigationPlaygroundApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun ComposeNavigationPlaygroundApp() {
    val appBackstack = rememberSaveable {
        mutableStateListOf<AppDestination>(AppDestination.Home)
    }

    var userName by rememberSaveable { mutableStateOf("Android") }

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
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val baseModifier = Modifier.padding(innerPadding)

            NavDisplay(
                backStack = appBackstack,
                onBack = { appBackstack.removeLastOrNull()},
                entryProvider = { key ->
                    when (key) {
                        is AppDestination.Home -> NavEntry(key) {
                            HomeScreen(
                                name = userName,
                                modifier = baseModifier
                            )
                        }

                        is AppDestination.Favorites -> NavEntry(key) {
                            FavoritesScreen(modifier = baseModifier)
                        }

                        is AppDestination.Profile -> NavEntry(key) {
                            ProfileScreen(modifier = baseModifier)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun HomeScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.fillMaxSize()
    )
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
    Text(
        text = "Favorites",
        modifier = modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen()
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Profile",
        modifier = modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}