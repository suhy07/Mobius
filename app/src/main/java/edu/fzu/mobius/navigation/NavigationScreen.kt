package edu.fzu.mobius.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.ui.capsule.CapsuleScreen
import edu.fzu.mobius.ui.write.WriteMailScreen
import edu.fzu.mobius.ui.write.WriteMailViewModel
import edu.fzu.mobius.ui.mailbox.AnonMailBoxScreen
import edu.fzu.mobius.ui.mailbox.MailBoxScreen
import edu.fzu.mobius.ui.mailbox.MyMailBoxScreen
import edu.fzu.mobius.ui.mine.MineScreen
import edu.fzu.mobius.ui.penpal.PenPalScreen
import edu.fzu.mobius.ui.write.WritePenPalScreen

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    val writeMailViewModel : WriteMailViewModel= viewModel()
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
        composable("write_mail_screen"){
            WriteMailScreen(
                navController = navController,
                items = writeMailViewModel.lineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "陌生人"
            )
        }
        composable("anon_mailbox_screen"){
            AnonMailBoxScreen(navController = navController)
        }
        composable("pen_pal_screen"){
            PenPalScreen(navController = navController)
        }
        composable("write_pen_pal_screen"){
            WritePenPalScreen(
                navController = navController,
                items = writeMailViewModel.lineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "笔友一号"
            )
        }
        composable("pen_pal_invite_screen"){
            CapsuleScreen(navController = navController)
        }
        composable("capsule_screen"){
            CapsuleScreen(navController = navController)
        }
        composable("mine_screen"){
            MineScreen(navController = navController)
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewNavigation() {
    NavigationScreen()
}