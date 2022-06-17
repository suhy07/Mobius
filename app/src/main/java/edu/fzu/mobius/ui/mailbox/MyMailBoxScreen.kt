package edu.fzu.mobius.ui.mailbox

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.mailbox.item.Envelope
import edu.fzu.mobius.compose.mailbox.item.OtherUser
import edu.fzu.mobius.theme.BlueBackground

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MyMailBoxScreen(navController: NavController){
    Scaffold(
        backgroundColor = BlueBackground,
        topBar = {
            BaseTitleTop(
                navController = navController,
                router = "mailbox_screen",
                title = "我的信箱"
            )
        }
    ){
        val mailBoxViewModel: MailBoxViewModel = viewModel()
        mailBoxViewModel.getReceivedMailBox()
        LazyColumn {
            items(mailBoxViewModel.letters){
                Envelope(
                    userNickname = it.userNickname,
                    abstract = it.abstract,
                    otherNickname = it.otherNickname,
                    type = it.type,
                )
            }
        }
    }
}
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewMyMailBox() {
    val navController = rememberNavController()
    MyMailBoxScreen(navController = navController)
}