package edu.fzu.mobius.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FirstScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "First Screen\n" +
                    "Click me to go to Second Screen",
            color = Color.Green,
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier.padding(24.dp).clickable(onClick = {
                // this will navigate to second screen
                navController.navigate("second_screen")
            })
        )
    }
}

@Composable
fun SecondScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Second Screen\n" +
                    "Click me to go to Third Screen",
            color = Color.Yellow,
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier.clickable(onClick = {
                // this will navigate to third screen
                navController.navigate("third_screen")
            })
        )
    }
}

@Composable
fun ThirdScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Third Screen\n" +
                    "Click me to go to First Screen",
            color = Color.Red,
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier.clickable(onClick = {
                // this will navigate to first screen
                navController.navigate("first_screen")
            })
        )
    }
}
