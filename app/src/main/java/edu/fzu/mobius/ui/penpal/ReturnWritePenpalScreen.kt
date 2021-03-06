package edu.fzu.mobius.ui.penpal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.base.NoShadowButton
import edu.fzu.mobius.compose.penpal.AddPenOtherUser
import edu.fzu.mobius.compose.penpal.PenItem
import edu.fzu.mobius.entity.ReceiveProject
import edu.fzu.mobius.entity.StrangeDataProject
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.ui.common.NoShadowTopAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.ui.common.nav.float.NavFloatButton
import edu.fzu.mobius.ui.mail.LineItem

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun ReturnWritePenpalScreen(
    navController: NavController,
    items:List<LineItem>,
    onEditItemChange: (LineItem) -> Unit,
    otherNickname: String,
    returnWritePenPalViewModel: ReturnWritePenPalViewModel,
    receivelist: MutableState<List<ReceiveProject>>,
    getlistReceived: ()->Unit,
) {
    val returnWritePenPalViewModel: ReturnWritePenPalViewModel = viewModel()
    var floatingVisible = returnWritePenPalViewModel.floatingVisible
    getlistReceived()
    Scaffold(
        topBar = {
            NoShadowTopAppBar (
                modifier = Modifier
                    .height(220.dp)//TopBar?????????   ????????????????????????modifier???
                    .fillMaxWidth()
                    ){
                ConstraintLayout {
                    val (back,title_,top_,name_,time_,mail_) = createRefs()
                    NoShadowButton(
                        onClick = {
                            singleTaskNav(navController,"pen_pal_screen")
                        },
                        modifier = Modifier
                            .constrainAs(back) {
                                top.linkTo(parent.top, margin = 0.dp)
                                start.linkTo(parent.start, margin = 0.dp)
                                bottom.linkTo(parent.bottom, margin = 170.dp)
                            }
                    ) {
                        UnspecifiedIcon(
                            painter = painterResource(id = R.mipmap.back_left_icon),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .padding(start = 20.dp)
                        )
                    }
                    Text(
                        text = "????????????",
                        modifier = Modifier
                            .constrainAs(title_) {
                                top.linkTo(parent.top, margin = 0.dp)
                                start.linkTo(parent.start, margin = 310.dp)
                                end.linkTo(parent.end, margin = 0.dp)
                                bottom.linkTo(parent.bottom, margin = 170.dp)
                            }.clickable(
                                enabled = true,
                                role = Role.Button
                            ){
                                returnWritePenPalViewModel.DeleteStranger(navController)
                            }
                        ,
                        textAlign = TextAlign.Right,
                        color = Color(0xFF17418B),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                    UnspecifiedIcon(
                        painter = painterResource(id = R.mipmap.head_girl),
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .constrainAs(top_) {
                                start.linkTo(parent.start, margin = 0.dp)
                                end.linkTo(parent.end, margin = 220.dp)
                                top.linkTo(parent.top, margin = 0.dp)
                                bottom.linkTo(parent.bottom, margin = 10.dp)
                            }
                    )
                    Text(
                        text = "hzd",
                        modifier = Modifier
                            .constrainAs(name_) {
                                top.linkTo(parent.top, margin = 0.dp)
                                start.linkTo(parent.start, margin = 0.dp)
                                end.linkTo(parent.end, margin = 0.dp)
                                bottom.linkTo(parent.bottom, margin = 50.dp)
                            },
                        textAlign = TextAlign.Left,
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = "??????????????????100???",
                        modifier = Modifier
                            .constrainAs(time_) {
                                top.linkTo(parent.top, margin = 20.dp)
                                start.linkTo(parent.start, margin = 150.dp)
                                end.linkTo(parent.end, margin = 0.dp)
                                bottom.linkTo(parent.bottom, margin = 0.dp)
                            },
                        textAlign = TextAlign.Left,
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = "|  ????????????",
                        modifier = Modifier
                            .constrainAs(mail_) {
                                top.linkTo(parent.top, margin = 180.dp)
                                start.linkTo(parent.start, margin = 0.dp)
                                end.linkTo(parent.end, margin = 240.dp)
                                bottom.linkTo(parent.bottom, margin = 0.dp)
                            },
                        textAlign = TextAlign.Left,
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
        },
        bottomBar = {

        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = floatingVisible.value,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight*2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight*2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ) {
                NavFloatButton(
                    resource = R.drawable.lnk,
                    onClick = {
                        navController.navigate("write_capsule_screen")
                    }
                )
            }
        }
    ) {
        ConstraintLayout() {
            LazyColumn {
                items(receivelist.value){
                    PenItem(
                        userNickname = it.nickName,
                        abstract = it.contentbrief,
                        otherNickname = it.receiverNickname,
                        type = 1,
                        modifier = Modifier.animateItemPlacement())
                }
                item {
                    PenItem(userNickname = "hzd",
                        abstract = "?????????????????????????????????..",
                        otherNickname = "????????????",
                        type = 1,
                        modifier = Modifier.animateItemPlacement())
                }
                item {
                    PenItem(userNickname = "????????????", abstract = "????????????????????????????????????", otherNickname = "hzd", type = 1, modifier = Modifier.animateItemPlacement())
                }
                item {
                    PenItem(userNickname = "hzd", abstract = "????????????????????????????????????", otherNickname = "????????????", type = 1, modifier = Modifier.animateItemPlacement())
                }
            }
        }
    }
}
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewReturn(){
    val lists = listOf(LineItem(""), LineItem(""))

    val returnWritePenPalViewModel: ReturnWritePenPalViewModel = viewModel()
    ReturnWritePenpalScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = {(LineItem)->{}},
        otherNickname ="????????????",
        returnWritePenPalViewModel = returnWritePenPalViewModel,
        receivelist = returnWritePenPalViewModel.receivelist,
        getlistReceived = returnWritePenPalViewModel::getlistReceived,
    )
}