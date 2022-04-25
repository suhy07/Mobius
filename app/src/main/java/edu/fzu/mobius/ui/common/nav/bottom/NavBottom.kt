package edu.fzu.mobius.ui.common.nav.bottom

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R

@Composable
fun NavBottom(navController: NavController, act:Int){
    BottomAppBar(
        backgroundColor = Color.Unspecified,
        modifier = Modifier
            .padding(
                start = 12.dp,
                end = 12.dp,
                top = 0.dp,
                bottom = 0.dp
            )
            .height(70.dp),
        elevation = 55.dp,
        contentPadding = PaddingValues( start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
    ) {
        Card(
            shape = RoundedCornerShape(topStart = 20.dp,topEnd = 20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(0.dp),
        ) {
            Row(Modifier.fillMaxWidth().padding(0.dp),) {
                var mailboxIcon = R.mipmap.mailbox_icon
                var penPalIcon = R.mipmap.penpal_icon
                var capsuleIcon = R.mipmap.capsule_icon
                var userIcon = R.mipmap.user_icon
                when{
                    act == 0 -> mailboxIcon =R.mipmap.mailbox_act_icon
                    act == 1 -> penPalIcon = R.mipmap.penpal_act_icon
                    act == 2 -> capsuleIcon = R.mipmap.capsule_act_icon
                    act == 3 -> userIcon = R.mipmap.user_act_icon
                }
                NavButton(
                    navController =navController ,
                    router = "mailbox_screen" ,
                    icon = userIcon,
                    name = "信箱",
                    modifier = Modifier.weight(1f)
                )
                NavButton(
                    navController =navController ,
                    router = "pen_pal_screen" ,
                    icon = penPalIcon,
                    name = "笔友",
                    modifier = Modifier.weight(1f)
                )
                NavButton(
                    navController =navController ,
                    router = "capsule_screen" ,
                    icon = capsuleIcon,
                    name = "胶囊",
                    modifier = Modifier.weight(1f)
                )
                NavButton(
                    navController =navController ,
                    router = "mine_screen" ,
                    icon = userIcon,
                    name = "我的",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewNavBottom() {
    val navController = rememberNavController()
    NavBottom(navController = navController, act = 0)
}