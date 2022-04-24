package edu.fzu.mobius.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

enum class DemoScreen {
    HomeScreen,
    EditScreen
}
@Composable
fun DemoNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = DemoScreen.HomeScreen.name
    ) {
        composable(DemoScreen.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(DemoScreen.EditScreen.name) {
            EditScreen(navController = navController)
        }
    }
}

@Composable
fun HomeScreen(navController : NavHostController) {
    Scaffold(
        drawerContent = { /*...*/ },
        topBar = { /*...*/ },
        content = {
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(DemoScreen.EditScreen.name)
            }) {
                Icon(Icons.Filled.Add,"")
            }
        }
    )
}

@Composable
fun EditScreen(navController: NavController) {
    Button(onClick = { /*TODO*/ }) {
        navController.navigate(DemoScreen.HomeScreen.name)
    }
}