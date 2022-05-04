package edu.fzu.mobius.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.ui.capsule.CapsuleScreen
import edu.fzu.mobius.ui.login.LoginScreen
import edu.fzu.mobius.ui.login.LoginViewModel
import edu.fzu.mobius.ui.write.WriteMailScreen
import edu.fzu.mobius.ui.write.WriteMailViewModel
import edu.fzu.mobius.ui.mailbox.AnonMailBoxScreen
import edu.fzu.mobius.ui.mailbox.MailBoxScreen
import edu.fzu.mobius.ui.mailbox.MyMailBoxScreen
import edu.fzu.mobius.ui.mine.MineScreen
import edu.fzu.mobius.ui.mine.MineViewModel
import edu.fzu.mobius.ui.penpal.InviteSuccessScreen
import edu.fzu.mobius.ui.penpal.PenPalScreen
import edu.fzu.mobius.ui.penpal.ReturnWritePenpalScreen
import edu.fzu.mobius.ui.register.RegisterScreen
import edu.fzu.mobius.ui.register.RegisterViewModel
import edu.fzu.mobius.ui.register.SetNicknameScreen
import edu.fzu.mobius.ui.write.WriteCapsuleScreen
import edu.fzu.mobius.ui.write.WritePenPalScreen

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    val loginViewModel : LoginViewModel = viewModel()
    val registerViewModel : RegisterViewModel = viewModel()
    val writeMailViewModel : WriteMailViewModel = viewModel()
    val mineViewModel : MineViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "login_screen"
    ) {
        composable("login_screen"){
            LoginScreen(
                navController = navController,
                phoneNumber = loginViewModel.phoneNumber,
                verificationCode = loginViewModel.verificationCode,
                password = loginViewModel.password,
                login = loginViewModel::login,
                sendVerificationCode = loginViewModel::sendVerificationCode
            )
        }
        composable("register_screen"){
            RegisterScreen(
                navController = navController,
                phoneNumber = registerViewModel.phoneNumber,
                verificationCode = registerViewModel.verificationCode,
                password = registerViewModel.password,
                passwordRepeat = registerViewModel.passwordRepeat,
                register = registerViewModel::register,
                sendVerificationCode = registerViewModel::sendVerificationCode
            )
        }
        composable("set_nickname_screen"){
            SetNicknameScreen(
                navController = navController,
                nickname = registerViewModel.nickname,
                setNickname = registerViewModel::setNickname
            )
        }
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
            InviteSuccessScreen(navController = navController)
        }
        composable("return_write_pen_pal_screen"){
            ReturnWritePenpalScreen(navController = navController,
                items = writeMailViewModel.lineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "笔友一号")
        }
        composable("capsule_screen"){
            CapsuleScreen(navController = navController)
        }
        composable("write_capsule_screen"){
            WriteCapsuleScreen(navController = navController,
                items = writeMailViewModel.lineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "")
        }
        composable("mine_screen"){
            MineScreen(
                navController = navController,
                nickname = mineViewModel.nickname,
                tamp = mineViewModel.tamp,
                grow = mineViewModel.grow
            )
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