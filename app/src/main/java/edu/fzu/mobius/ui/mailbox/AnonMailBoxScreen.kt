package edu.fzu.mobius.ui.mailbox

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.mailbox.item.Envelope
import edu.fzu.mobius.entity.Letter
import edu.fzu.mobius.navigation.navigateAndArgument
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.theme.BlueBackground

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun AnonMailBoxScreen(
    navController: NavController,
    letters: List<Letter>,
){
    Scaffold(
        backgroundColor = BlueBackground,
        topBar = {
            BaseTitleTop(
                navController = navController,
                router = "mailbox_screen",
                title = "陌生人邮箱"
            )
        }
    ){
        LazyColumn {
            items(letters){
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
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewAnonMailBox() {
    val navController = rememberNavController()
    val anonMailBoxViewModel: AnonMailBoxViewModel = viewModel()
    AnonMailBoxScreen(
        navController = navController,
        letters = anonMailBoxViewModel.anonList,
    )
}