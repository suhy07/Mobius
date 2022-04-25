package edu.fzu.mobius.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.ui.FirstScreen
import edu.fzu.mobius.ui.SecondScreen
import edu.fzu.mobius.ui.ThirdScreen
import edu.fzu.mobius.ui.mailbox.MailBoxScreen

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "mailbox_screen"
    ) {
        composable("mailbox_screen"){
            MailBoxScreen(navController = navController)
        }
        composable("first_screen") {
            FirstScreen(navController = navController)
        }
        composable("second_screen") {
            SecondScreen(navController = navController)
        }
        composable("third_screen") {
            ThirdScreen(navController = navController)
        }
    }
}
