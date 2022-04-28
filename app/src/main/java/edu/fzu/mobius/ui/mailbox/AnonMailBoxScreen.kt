package edu.fzu.mobius.ui.mailbox

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.ui.common.mailbox.item.Envelope
import edu.fzu.mobius.ui.common.mailbox.top.MailBoxTop
import edu.fzu.mobius.ui.theme.BlueBackground

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun AnonMailBoxScreen(navController: NavController){
    Scaffold(
        backgroundColor = BlueBackground,
        topBar = { MailBoxTop(navController = navController, router = "mailbox_screen", title = "陌生人邮箱") }
    ){
        LazyColumn(){
            items(15) {
                Envelope(userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = 0)
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
    MyMailBoxScreen(navController = navController)
}