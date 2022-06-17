package edu.fzu.mobius.ui.mailbox

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.mailbox.item.Envelope
import edu.fzu.mobius.theme.BlueBackground

@ExperimentalAnimationApi
@Composable
fun SentMailBoxScreen(
    navController: NavController
) {
    Scaffold(
        backgroundColor = BlueBackground,
        topBar = {
            BaseTitleTop(
                navController = navController,
                router = "mine_screen",
                title = "我寄出的信"
            )
        }
    ){
        val mailBoxViewModel: MailBoxViewModel = viewModel()
        mailBoxViewModel.getSentMailBox()
        LazyColumn(){
            items(mailBoxViewModel.letters){
                Envelope(
                    id = it.id,
                    userNickname = it.userNickname,
                    abstract = it.abstract,
                    otherNickname = it.otherNickname,
                    type = it.type,
                    navController = navController
                )
            }
        }
    }
}