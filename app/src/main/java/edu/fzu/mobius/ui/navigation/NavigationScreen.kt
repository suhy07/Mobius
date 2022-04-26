package edu.fzu.mobius.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.ui.FirstScreen
import edu.fzu.mobius.ui.SecondScreen
import edu.fzu.mobius.ui.ThirdScreen
import edu.fzu.mobius.ui.capsule.CapsuleScreen
import edu.fzu.mobius.ui.mailbox.MailBoxScreen
import edu.fzu.mobius.ui.mine.MineScreen
import edu.fzu.mobius.ui.penpal.PenPalScreen

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "mailbox_screen"
    ) {
        composable("mailbox_screen"){
            MailBoxScreen(navController = navController)
        }
        composable("pen_pal_screen"){
            PenPalScreen(navController = navController)
        }
        composable("capsule_screen"){
            CapsuleScreen(navController = navController)
        }
        composable("mine_screen"){
            MineScreen(navController = navController)
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

@Preview
@Composable
fun PreviewNavigation() {
    NavigationScreen()
}