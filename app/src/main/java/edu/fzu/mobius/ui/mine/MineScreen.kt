package edu.fzu.mobius.ui.mine

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.navigation.NavigationScreen
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.ui.common.nav.bottom.NavBottom
import edu.fzu.mobius.theme.BlueBackground
import edu.fzu.mobius.ui.common.UnspecifiedIcon


@Composable
fun MineScreen(
    navController: NavController,
) {
    val mineViewModel: MineViewModel = viewModel()
    val nickname: MutableState<String> = mineViewModel.nickname
    val stamp: MutableState<Int> = mineViewModel.stamp
    val grow: MutableState<Int> = mineViewModel.grow
    mineViewModel.getUserInfo()
    Scaffold(
        backgroundColor = BlueBackground,
        bottomBar = {
            NavBottom(navController = navController, act = 3)
        },
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (editHead, _nickname, _stamp, _grow, card1, card2) = createRefs()
            Box(
                modifier = Modifier
                    .height(90.dp)
                    .width(90.dp)
                    .constrainAs(editHead) {
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 170.dp)
                        top.linkTo(parent.top, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 550.dp)
                    }
            ) {
                ConstraintLayout {
                    val (head, edit) = createRefs()
                    UnspecifiedIcon(
                        painter = painterResource(id = R.mipmap.head_girl),
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .constrainAs(head) {
                            }
                    )
                    IconButton(
                        onClick = { navController.navigate("set_nickname_screen") },
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .constrainAs(edit) {
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }
                    ) {
                        UnspecifiedIcon(painter = painterResource(id = R.mipmap.edit_icon))
                    }
                }
            }
            Text(
                text = nickname.value,
                modifier = Modifier
                    .constrainAs(_nickname) {
                        top.linkTo(parent.top, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 585.dp)
                        start.linkTo(editHead.end, margin = 40.dp)
                    }
                    .clickable {
                        nickname.value += "123"
                    },
                fontSize = 20.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = "邮票：${stamp.value}",
                modifier = Modifier
                    .constrainAs(_stamp) {
                        top.linkTo(parent.top, margin = 60.dp)
                        bottom.linkTo(parent.bottom, margin = 585.dp)
                        start.linkTo(editHead.end, margin = 40.dp)
                    },
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "成长值：${grow.value}",
                modifier = Modifier
                    .constrainAs(_grow) {
                        top.linkTo(parent.top, margin = 110.dp)
                        bottom.linkTo(parent.bottom, margin = 585.dp)
                        start.linkTo(editHead.end, margin = 40.dp)
                    },
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Card(
                modifier = Modifier
                    .height(60.dp)
                    .width(350.dp)
                    .constrainAs(card1) {
                        top.linkTo(parent.top, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 300.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                    },
                shape = RoundedCornerShape(20.dp),
                elevation = 5.dp
            ) {
                ConstraintLayout {
                    val (item1,item2,spacer) = createRefs()
                    Card1Item(
                        icon = R.mipmap.stamp_icon,
                        title = "我寄出的信",
                        onClick = { navController.navigate("sent_mailbox_screen") },
                        modifier = Modifier
                            .constrainAs(item1){
                                start.linkTo(parent.start, margin = 10.dp)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    )
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(50.dp)
                            .background(Color(0xFFF7F6F9))
                            .constrainAs(spacer) {
                                start.linkTo(item1.end)
                                end.linkTo(item2.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    )
                    Card1Item(
                        icon = R.mipmap.suitcase_icon,
                        title = "草稿箱",
                        onClick = { navController.navigate("drafts_screen") },
                        modifier = Modifier
                            .constrainAs(item2){
                                end.linkTo(parent.end, margin = 10.dp)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    )
                }
            }
            Card(
                modifier = Modifier
                    .height(300.dp)
                    .width(350.dp)
                    .constrainAs(card2) {
                        top.linkTo(parent.top, margin = 120.dp)
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                    },
                shape = RoundedCornerShape(20.dp),
                elevation = 5.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 25.dp)
                ){
                    Card2Item(title = "邮票收集", onClick = { singleTaskNav(navController,"stamp_collect_screen") })
                    Card2Item(title = "修改密码", onClick = { singleTaskNav(navController ,"change_password_screen") })
                    Card2Item(title = "反馈", onClick = { singleTaskNav(navController ,"feedback_screen")})
                    Card2Item(title = "退出登陆", onClick = { singleTaskNav(navController,"login_screen") })
                }
            }
        }
    }
}

@Composable
fun Card1Item(
    icon: Int,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    TextButton(
        onClick = onClick,
        modifier = modifier
            .height(50.dp)
            .width(160.dp)
    ) {
        ConstraintLayout {
            val (_icon,_title) = createRefs()
            UnspecifiedIcon(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier
                    .height(30.dp)
                    .width(50.dp)
                    .constrainAs(_icon) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Text(
                text = title,
                modifier = Modifier
                    .width(80.dp)
                    .constrainAs(_title) {
                        start.linkTo(_icon.end, margin = 20.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                fontSize = 13.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}

@Composable
fun Card2Item(
    title: String,
    onClick: ()->Unit,
    modifier: Modifier = Modifier
){
    TextButton(
        onClick = onClick,
        modifier = modifier
            .width(300.dp)
            .height(60.dp)
    ) {
        ConstraintLayout {
            val (_title,spacer,arrow) = createRefs()
            Text(
                text = title,
                modifier = Modifier
                    .constrainAs(_title) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                fontSize = 14.sp,
            )
            Spacer(
                modifier = Modifier
                    .width(300.dp)
                    .height(1.dp)
                    .background(Color(0xFFF7F6F9))
                    .constrainAs(spacer) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(_title.bottom, margin = 18.dp)
                    }
            )
            UnspecifiedIcon(
                painter = painterResource(id = R.mipmap.back_right_icon),
                modifier = Modifier
                    .height(14.dp)
                    .constrainAs(arrow) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
            )
        }
    }
}

@Preview
@Composable
fun PreviewCard1(){
    Card1Item(
        icon = R.mipmap.stamp_icon,
        title = "我寄出的信",
        onClick = { /*TODO*/ }
    )
}

@Preview
@Composable
fun PreviewCard2(){
    Card2Item(
        title = "我寄出的信",
        onClick = { /*TODO*/ }
    )
}

@Preview
@Composable
fun PreviewMine() {
    val navController = rememberNavController()
    val mineViewModel:MineViewModel = viewModel()
    MineScreen(
        navController = navController,
    )
}


