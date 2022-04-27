package edu.fzu.mobius.ui.mailbox

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.ui.common.mailbox.item.Envelope
import edu.fzu.mobius.ui.common.mailbox.top.MailBoxTop
import edu.fzu.mobius.ui.theme.BlueBackground

@ExperimentalAnimationApi
@Composable
fun MyMailBoxScreen(navController: NavController){
    Scaffold(
        backgroundColor = BlueBackground,
        topBar = { MailBoxTop(navController = navController, router = "mailbox_screen", title = "我的信箱") }
    ){
        LazyColumn(){
            item {
                Envelope(userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1111111", type = 1)
            }
            items(2) {
                Envelope(userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = 2)
            }
            items(5) {
                Envelope(userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = 4)
            }
            items(2) {
                Envelope(userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = 3)
            }
            item {
                Envelope(userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = 4)
            }
        }
    }
}
@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewMyMailBox() {
    val navController = rememberNavController()
    MyMailBoxScreen(navController = navController)
}