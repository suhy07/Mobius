package edu.fzu.mobius.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.entity.Draft
import edu.fzu.mobius.ui.capsule.CapsuleScreen
import edu.fzu.mobius.ui.capsule.CapsuleSuccessScreen
import edu.fzu.mobius.ui.capsule.ReturnWriteCapsuleScreen
import edu.fzu.mobius.ui.draft.DraftEditScreen
import edu.fzu.mobius.ui.draft.DraftViewModel
import edu.fzu.mobius.ui.draft.DraftsScreen
import edu.fzu.mobius.ui.login.LoginScreen
import edu.fzu.mobius.ui.login.LoginViewModel
import edu.fzu.mobius.ui.write.WriteMailScreen
import edu.fzu.mobius.ui.write.WriteMailViewModel
import edu.fzu.mobius.ui.mailbox.AnonMailBoxScreen
import edu.fzu.mobius.ui.mailbox.MailBoxScreen
import edu.fzu.mobius.ui.mailbox.MyMailBoxScreen
import edu.fzu.mobius.ui.mailbox.SentMailBoxScreen
import edu.fzu.mobius.ui.mine.MineScreen
import edu.fzu.mobius.ui.mine.MineViewModel
import edu.fzu.mobius.ui.penpal.InviteSuccessScreen
import edu.fzu.mobius.ui.penpal.PenPalScreen
import edu.fzu.mobius.ui.penpal.ReturnWritePenpalScreen
import edu.fzu.mobius.ui.register.RegisterScreen
import edu.fzu.mobius.ui.register.RegisterViewModel
import edu.fzu.mobius.ui.register.SetNicknameScreen
import edu.fzu.mobius.ui.stamp.StampCollectScreen
import edu.fzu.mobius.ui.stamp.StampViewModel
import edu.fzu.mobius.ui.write.WriteCapsuleScreen
import edu.fzu.mobius.ui.write.WritePenPalScreen
import kotlin.math.min

val STARTNAV = "login_screen"
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val registerViewModel: RegisterViewModel = viewModel()
    val writeMailViewModel: WriteMailViewModel = viewModel()
    val mineViewModel: MineViewModel = viewModel()
    val stampViewModel: StampViewModel = viewModel()
    val draftViewModel: DraftViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = STARTNAV
    ) {
        composable("login_screen"){
            LoginScreen(
                navController = navController,
                phoneNumber = loginViewModel.phoneNumber,
                verificationCode = loginViewModel.verificationCode,
                password = loginViewModel.password,
                state = loginViewModel.state,
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
        composable("anon_mailbox_screen"){
            AnonMailBoxScreen(navController = navController)
        }
        composable("sent_mailbox_screen"){
            SentMailBoxScreen(navController = navController)
        }
        composable("write_mail_screen"){
            WriteMailScreen(
                navController = navController,
                items = writeMailViewModel.lineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "陌生人"
            )
        }
        composable("pen_pal_screen"){
            PenPalScreen(navController = navController)
        }
        composable("write_pen_pal_screen"){
            WritePenPalScreen(
                navController = navController,
                items = writeMailViewModel.lineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "笔友一号",
                card=false,
                sure=true,
            )
        }
        composable("pen_pal_invite_screen"){
            InviteSuccessScreen(navController = navController)
        }
        composable("return_write_pen_pal_screen"){
            WritePenPalScreen(
                navController = navController,
                items = writeMailViewModel.lineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "笔友一号",
                card=false,
                sure=false,
            )
        }
        composable("capsule_screen"){
            CapsuleScreen(navController = navController)
        }
        composable("write_capsule_screen"){
            WriteCapsuleScreen(
                navController = navController,
                items = writeMailViewModel.lineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "",
                card = false,
                sure = true,
                return1 = false,
            )
        }
        composable("capsule_success_screen"){
            CapsuleSuccessScreen(navController = navController)
        }
        composable("return_write_capsule_screen"){
            WriteCapsuleScreen(
                navController = navController,
                items = writeMailViewModel.lineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname ="你好友1",
                card = false,
                sure = false,
                return1 = true,
            )
        }
        composable("mine_screen"){
            MineScreen(
                navController = navController,
                nickname = mineViewModel.nickname,
                stamp = mineViewModel.stamp,
                grow = mineViewModel.grow
            )
        }
        composable("stamp_collect_screen"){
            StampCollectScreen(
                navController = navController,
                stamps = stampViewModel.stamps
            )
        }
        composable("drafts_screen"){
            DraftsScreen(
                navController = navController,
                drafts = draftViewModel.drafts
            )
        }
        composable("draft_edit_screen"){
            DraftEditScreen(
                navController = navController,
                draft = Draft(0,"")
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

fun singleTaskNav(navController: NavController, router: String){
    navController.navigate(router){
        popUpTo(STARTNAV){ inclusive = true }
        launchSingleTop = true
    }
}