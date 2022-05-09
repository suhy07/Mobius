package edu.fzu.mobius.ui.mailbox

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
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
                title = "我寄出的邮"
            )
        }
    ){
        LazyColumn(){
            items(15) {
                Envelope(userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = 2)
            }
        }
    }
}