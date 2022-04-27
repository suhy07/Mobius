package edu.fzu.mobius.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.ui.capsule.CapsuleScreen
import edu.fzu.mobius.ui.mailbox.MailBoxScreen
import edu.fzu.mobius.ui.mailbox.MyMailBoxScreen
import edu.fzu.mobius.ui.mine.MineScreen
import edu.fzu.mobius.ui.penpal.PenPalScreen

@ExperimentalFoundationApi
@ExperimentalAnimationApi
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
        composable("my_mailbox_screen"){
            MyMailBoxScreen(navController = navController)
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
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewNavigation() {
    NavigationScreen()
}