package edu.fzu.mobius.ui.mailbox

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MyMailBoxScreen(navController: NavController){
    Scaffold(){

    }
}
@Preview
@Composable
fun PreviewMyMailBox() {
    val navController = rememberNavController()
    MyMailBoxScreen(navController = navController)
}