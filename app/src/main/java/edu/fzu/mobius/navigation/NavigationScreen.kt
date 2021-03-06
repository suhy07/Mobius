package edu.fzu.mobius.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.entity.Draft
import edu.fzu.mobius.global.GlobalMem
import edu.fzu.mobius.ui.anon.AnonSuccessScreen
import edu.fzu.mobius.ui.anon.ReadAnonMailViewModel
import edu.fzu.mobius.ui.capsule.CapsuleScreen
import edu.fzu.mobius.ui.capsule.CapsuleSuccessScreen
import edu.fzu.mobius.ui.draft.DraftEditScreen
import edu.fzu.mobius.ui.draft.DraftViewModel
import edu.fzu.mobius.ui.draft.DraftsScreen
import edu.fzu.mobius.ui.feedback.FeedbackScreen
import edu.fzu.mobius.ui.feedback.FeedbackViewModel
import edu.fzu.mobius.ui.login.LoginScreen
import edu.fzu.mobius.ui.login.LoginViewModel
import edu.fzu.mobius.ui.mailbox.*
import edu.fzu.mobius.ui.mine.MineScreen
import edu.fzu.mobius.ui.mine.MineViewModel
import edu.fzu.mobius.ui.password.ChangePasswordScreen
import edu.fzu.mobius.ui.password.ChangePasswordViewModel
import edu.fzu.mobius.ui.penpal.*
import edu.fzu.mobius.ui.register.RegisterScreen
import edu.fzu.mobius.ui.register.RegisterViewModel
import edu.fzu.mobius.ui.register.SetNicknameScreen
import edu.fzu.mobius.ui.stamp.StampCollectScreen
import edu.fzu.mobius.ui.stamp.StampViewModel
import edu.fzu.mobius.ui.mail.*

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun NavigationScreen() {

    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val registerViewModel: RegisterViewModel = viewModel()
    val changePasswordViewModel: ChangePasswordViewModel = viewModel()
    val penPalViewModel: PenPalViewModel = viewModel()
    val writeCapsuleViewModel: WriteCapsuleViewModel = viewModel()
    val writePenPalViewModel: WritePenPalViewModel = viewModel()
    val writeMailViewModel: WriteMailViewModel = viewModel()
    val stampViewModel: StampViewModel = viewModel()
    val anonMailBoxViewModel: AnonMailBoxViewModel = viewModel()
    val returnWritePenPalViewModel: ReturnWritePenPalViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = GlobalMem.START_NAV
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
        composable("change_password_screen"){
            ChangePasswordScreen(
                navController = navController,
                phoneNumber = changePasswordViewModel.phoneNumber,
                verificationCode = changePasswordViewModel.verificationCode,
                password = changePasswordViewModel.password,
                passwordRepeat = changePasswordViewModel.passwordRepeat,
                change = changePasswordViewModel::change,
                sendVerificationCode = changePasswordViewModel::sendVerificationCode
            )
        }
        composable("mailbox_screen"){
            MailBoxScreen(navController = navController)
        }
        composable("my_mailbox_screen"){
            MyMailBoxScreen(navController = navController)
        }
        composable("anon_mailbox_screen"){
            AnonMailBoxScreen(
                navController = navController,
                letters = anonMailBoxViewModel.anonList,
            )
        }
        composable("sent_mailbox_screen"){
            SentMailBoxScreen(navController = navController)
        }
        composable("write_anon_mail_screen"){
            WriteAnonMailScreen(
                navController = navController,
                otherNickname = "?????????"
            )
        }
        composable("read_anon_mail_screen/{id}"){
            navArgument("id"){
                type = NavType.IntType
                defaultValue = 0
            }
            it.arguments?.getInt("id")?.let { it1 ->
                ReadAnonMailScreen(
                    id = it1,
                    navController = navController
                )
            }
        }
        composable("anon_success_screen"){
            AnonSuccessScreen(navController = navController)
        }
        composable("pen_pal_screen"){
            PenPalScreen(
                navController = navController,
                friendlist = penPalViewModel.friendlist,
                strangelist = penPalViewModel.strangelist,
                strangeNickName = penPalViewModel.strangenickname,
                penPalList = penPalViewModel::PenPalList,
                addPenPalList = penPalViewModel::AddPenPalList
            )
        }
        composable("write_pen_pal_screen"){
            writeMailViewModel.letterValue.value=""
            WritePenPalScreen(
                navController = navController,
                items = writeMailViewModel.LineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "????????????",
                card=false,
                sure=true,
                topcard = false,
                topsure = true,
                cut = false,
                nav = "pen_pal_invite_screen",
                sendPenPalMail = writePenPalViewModel::sendPenPalMail,
                writePenPalViewModel = writePenPalViewModel,
                value = writeMailViewModel.letterValue
            )
        }
        composable("pen_pal_invite_screen"){
            InviteSuccessScreen(navController = navController)
        }
        composable("return_write_pen_pal_screen"){
            WritePenPalScreen(
                navController = navController,
                items = writeMailViewModel.LineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "????????????",
                card=false,
                sure=false,
                topcard = false,
                topsure = true,
                cut = false,
                nav = "pen_pal_invite_screen",
                sendPenPalMail = writePenPalViewModel::sendPenPalMail,
                writePenPalViewModel = writePenPalViewModel,
                value = writeMailViewModel.letterValue
            )
        }
        composable("revert_pen_pal_screen"){
            ReturnWritePenpalScreen(
                navController = navController,
                items = writeMailViewModel.LineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "????????????",
                returnWritePenPalViewModel = returnWritePenPalViewModel,
                receivelist = returnWritePenPalViewModel.receivelist,
                getlistReceived = returnWritePenPalViewModel::getlistReceived,
            )
        }
        composable("write_revert_pen_pal_screen"){
            WritePenPalScreen(
                navController = navController,
                items = writeMailViewModel.LineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "hzd",
                card=false,
                sure=true,
                topcard = true,
                topsure = false,
                cut = false,
                nav = "send_penpal_success_screen",
                sendPenPalMail = writePenPalViewModel::sendPenPalMail,
                writePenPalViewModel = writePenPalViewModel,
                value = writeMailViewModel.letterValue
            )
        }
        composable("cut_write_revert_pen_pal_screen"){
            WritePenPalScreen(
                navController = navController,
                items = writeMailViewModel.LineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "hzd",
                card=false,
                sure=false,
                topcard = true,
                topsure = false,
                cut = true,
                nav = "pen_pal_invite_screen",
                sendPenPalMail = writePenPalViewModel::sendPenPalMail,
                writePenPalViewModel = writePenPalViewModel,
                value = writeMailViewModel.letterValue
            )
        }

        composable("look_write_revert_pen_pal_screen"){
            WritePenPalScreen(
                navController = navController,
                items = writeMailViewModel.LineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "hzd",
                card=false,
                sure=false,
                topcard = false,
                topsure = true,
                cut = false,
                nav = "pen_pal_invite_screen",
                sendPenPalMail = writePenPalViewModel::sendPenPalMail,
                writePenPalViewModel = writePenPalViewModel,
                value = writeMailViewModel.letterValue
            )
        }
        composable("send_penpal_success_screen"){
            SendPenPalSuccessScreen(navController = navController)
        }
        composable("capsule_screen"){
            CapsuleScreen(navController = navController)
        }
        composable("write_capsule_screen"){
            writeMailViewModel.letterValue.value=""
            WriteCapsuleScreen(
                navController = navController,
                items = writeMailViewModel.LineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname = "",
                card = false,
                sure = true,
                return1 = false,
                sendWriteCapsule = writeCapsuleViewModel::sendWriteCapsule,
                writeCapsuleViewModel = writeCapsuleViewModel,
                value = writeMailViewModel.letterValue
            )
        }
        composable("capsule_success_screen"){
            CapsuleSuccessScreen(
                navController = navController,
                writeCapsuleViewModel = writeCapsuleViewModel,
            )
        }
        composable("return_write_capsule_screen"){
            WriteCapsuleScreen(
                navController = navController,
                items = writeMailViewModel.LineItems ,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                otherNickname ="?????????",
                card = false,
                sure = false,
                return1 = true,
                sendWriteCapsule = writeCapsuleViewModel::sendWriteCapsule,
                writeCapsuleViewModel = writeCapsuleViewModel,
                value = writeMailViewModel.letterValue
            )
        }
        composable("mine_screen"){
            MineScreen(
                navController = navController,
            )
        }
        composable("stamp_collect_screen"){
            StampCollectScreen(
                navController = navController,
                stamps = stampViewModel.stamps
            )
        }
        composable("feedback_screen"){
            FeedbackScreen(
                navController = navController,
            )
        }
        composable("drafts_screen"){
            DraftsScreen(
                navController = navController,
            )
        }
        composable("draft_edit_screen/{id}"
        ){
            navArgument("id"){
                type = NavType.IntType
                defaultValue = 0
            }
            it.arguments?.getInt("id")?.let { it1 ->
                DraftEditScreen(
                    navController = navController,
                    id = it1
                )
            }
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
        popUpTo(GlobalMem.START_NAV){ inclusive = true }
        launchSingleTop = true
    }
    when(router){
        "anon_mailbox_screen"->{
            AnonMailBoxViewModel.getAnonList()
        }
        "mailbox_screen"->{
            MineViewModel.getUserInfo()
        }
    }
}

fun NavController.navigateAndArgument(
    route: String,
    args: List<Pair<String, Any>>? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,

    ) {
    navigate(route = route, navOptions = navOptions, navigatorExtras = navigatorExtras)

    if (args == null && args?.isEmpty() == true) {
        return
    }

    val bundle = backQueue.lastOrNull()?.arguments
    if (bundle != null) {
        bundle.putAll(bundleOf(*args?.toTypedArray()!!))
    } else {
        println("The last argument of NavBackStackEntry is NULL")
    }
}