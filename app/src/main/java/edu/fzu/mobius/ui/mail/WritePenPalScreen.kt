package edu.fzu.mobius.ui.mail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.MailEditor
import edu.fzu.mobius.ui.common.NoShadowBottomAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.compose.penpal.PenOtherUser2
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.theme.bluetext
import edu.fzu.mobius.ui.common.NoShadowTopAppBar
import edu.fzu.mobius.ui.penpal.PenPalViewModel

@ExperimentalFoundationApi
@Composable
fun WritePenPalScreen(
    navController: NavController,
    items:List<LineItem>,
    onEditItemChange: (LineItem) -> Unit,
    otherNickname: String,
    card:Boolean,
    sure:Boolean,
    topcard:Boolean,
    topsure:Boolean,
    cut:Boolean,
    nav:String,
    sendPenPalMail: (NavController)->Unit,
    writePenPalViewModel:WritePenPalViewModel,
    value: MutableState<String> = mutableStateOf("test"),
) {

    var cardVisible by remember { mutableStateOf(false) }
    var sureVisible by remember { mutableStateOf(true) }
    var topcardVisible by remember { mutableStateOf(false) }
    var topsureVisible by remember { mutableStateOf(true) }
    var cutVisible by remember { mutableStateOf(false) }
    topcardVisible = topcard
    topsureVisible = topsure
    cardVisible = card
    sureVisible = sure
    cutVisible = cut
    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = topsureVisible,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ){
            BaseTitleTop(
                navController = navController,
                router = "pen_pal_screen"
            )
            }
            AnimatedVisibility(
                visible = topcardVisible,
                modifier = Modifier

                    .background(Color.Unspecified),

                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ) {
                NoShadowTopAppBar (
                    modifier = Modifier
                        .height(150.dp)//TopBar的高度   相关属性都可以用modifier改
                        .fillMaxWidth()
                ) {
                    ConstraintLayout {
                        val (back, title_, top_, name_, time_, mail_) = createRefs()
                        NoShadowButton(
                            onClick = {
                                singleTaskNav(navController, "revert_pen_pal_screen")
                            },
                            modifier = Modifier
                                .constrainAs(back) {
                                    top.linkTo(parent.top, margin = 0.dp)
                                    start.linkTo(parent.start, margin = 10.dp)
                                    bottom.linkTo(parent.bottom, margin = 90.dp)
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

                            text = otherNickname+" 收",

                            modifier = Modifier
                                .constrainAs(title_) {
                                    top.linkTo(parent.top, margin = 0.dp)
                                    start.linkTo(parent.start, margin = 100.dp)
                                    end.linkTo(parent.end, margin = 0.dp)
                                    bottom.linkTo(parent.bottom, margin = 90.dp)
                                },
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif
                        )
                        TextButton(
                            onClick = { /*TODO 跳转*/
                                singleTaskNav(navController, "cut_write_revert_pen_pal_screen")
                            },
                            shape = RoundedCornerShape(10.dp),
                            elevation = ButtonDefaults.elevation(10.dp, 10.dp, 10.dp),
                            colors = ButtonDefaults.textButtonColors(
                                backgroundColor = Color(0xFFF2F1F6),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .height(55.dp)
                                .width(300.dp)
                                .constrainAs(top_) {
                                    top.linkTo(parent.top, margin = 70.dp)
                                    start.linkTo(parent.start, margin = 40.dp)
                                    bottom.linkTo(parent.bottom, margin = 0.dp)
                                }
                        ) {
                            Text(
                                text = "你好,见字如面",
                                fontSize = 18.sp,
                                color = Color(0xFFB5B5B5)
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = sureVisible,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ) {
                NoShadowBottomAppBar(
                    modifier = Modifier
                        .padding(12.dp)
                        .height(120.dp)


                ) {
                    Column() {
                        LazyColumn(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                                .padding(start = 200.dp, top = 0.dp)
                        ) {
                            items(1) {
                                PenOtherUser2(nickname = "黄埔铁牛",
                                    modifier = Modifier.animateItemPlacement())
                            }
                        }

                        TextButton(
                            onClick = { /*TODO*/
                                cardVisible = true
                                sureVisible = false
                            },
                            shape = RoundedCornerShape(20.dp),
                            elevation = ButtonDefaults.elevation(10.dp, 10.dp, 10.dp),
                            colors = ButtonDefaults.textButtonColors(
                                backgroundColor = BlueButton,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "发送邀请",
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
            AnimatedVisibility(
                visible = cardVisible,
                modifier = Modifier

                    .background(Color.Unspecified),

                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ) { // this: AnimatedVisibilityScope
                // Use AnimatedVisibilityScope#transition to add a custom animation
                // to the AnimatedVisibility.
                Card(
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(Color.Unspecified)
                ) {
                    ConstraintLayout() {
                        val (cardtext, slider, cardtext1, cardbutton) = createRefs()
                        val progress = remember { mutableStateOf(0f) }
                        Text(
                            text = "设置忧郁值"+"   "+(progress.value*100).toInt()+"%",
                            modifier = Modifier
                                .constrainAs(cardtext) {
                                    start.linkTo(parent.start, margin = 15.dp)
                                    top.linkTo(parent.top, margin = 25.dp)
                                },
                            fontSize = 16.sp,)
                        Slider(
                            value = progress.value,
                            onValueChange = {
                                progress.value = it
                            },
                            colors = SliderDefaults.colors(
                                thumbColor = Color.Blue,
                                inactiveTrackColor = Color.LightGray,
                                activeTrackColor = Color.Blue
                            ),
                            modifier = Modifier
                                .constrainAs(slider) {
                                    start.linkTo(parent.start, margin = 5.dp)
                                    top.linkTo(parent.top, margin = 55.dp)
                                },
                        )

                        Text(
                            text = "每次邀请使用一张邮票",
                            modifier = Modifier
                                .constrainAs(cardtext1) {
                                    start.linkTo(parent.start, margin = 15.dp)
                                    top.linkTo(parent.top, margin = 105.dp)
                                },
                            fontSize = 14.sp,
                            color = bluetext,)
                        TextButton(
                            onClick = { /*TODO 发送成功*/
                                //跳转成功页面
//                                navController.navigate(nav)

                                writePenPalViewModel.title.value = "给笔友的信"
                                writePenPalViewModel.content.value = value.value
//                                Log.e("AAAAAA",writePenPalViewModel.content.value)
//                                Log.e("AAAAAA",writePenPalViewModel.receiverId.value.toString())
//                                Log.e("AAAAAA",writePenPalViewModel.title.value)
                                sendPenPalMail(navController)
                            },

                            shape = RoundedCornerShape(20.dp),
                            elevation = ButtonDefaults.elevation(10.dp, 10.dp, 10.dp),
                            colors = ButtonDefaults.textButtonColors(
                                backgroundColor = BlueButton,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .height(60.dp)
                                .width(360.dp)
                                .constrainAs(cardbutton) {
                                    start.linkTo(parent.start, margin = 25.dp)
                                    top.linkTo(parent.top, margin = 145.dp)
                                },
                        ) {
                            Text(
                                text = "确定",
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
            AnimatedVisibility(
                visible = cutVisible,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ) {
                NoShadowBottomAppBar(
                    modifier = Modifier
                        .padding(12.dp)
                        .height(120.dp)


                ) {
                    Column() {
                        LazyColumn(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                                .padding(start = 200.dp, top = 0.dp)
                        ) {
                            items(1) {
                                PenOtherUser2(nickname = "黄埔铁牛",
                                    modifier = Modifier.animateItemPlacement())
                            }
                        }

                        TextButton(
                            onClick = { /*TODO 跳转*/
                                navController.navigate("write_revert_pen_pal_screen")
                            },
                            shape = RoundedCornerShape(10.dp),
                            elevation = ButtonDefaults.elevation(10.dp, 10.dp, 10.dp),
                            colors = ButtonDefaults.textButtonColors(
                                backgroundColor = Color(0xFFF2F1F6),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .height(65.dp)
                                .fillMaxWidth()
                        ) {
                            Column {
                                Text(
                                    text = "To:黄埔铁牛",
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Left,
                                    color = Color(0xFFB5B5B5)
                                )
                                Text(
                                    text = "这里是我回复的话",
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Left,
                                    color = Color(0xFFB5B5B5)
                                )
                            }
                        }
                    }
                }
            }
        }
    ) {
        ConstraintLayout() {
            val (edit, card) = createRefs()
            MailEditor(
                otherNickname = otherNickname,
                items = items,
                onEditItemChange = onEditItemChange,
                modifier = Modifier
                    .constrainAs(edit) {
                    },
            )

        }
    }
}

// 04为邀请笔友的信  123为笔友页面的信件
@ExperimentalFoundationApi
@Preview
@Composable
fun PenPreviewWriteMail(){
    val lists = listOf(LineItem(""), LineItem(""))
    val writePenPalViewModel: WritePenPalViewModel = viewModel()

    WritePenPalScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = {(LineItem)->{}},
        otherNickname ="笔友一号",
        card=false,
        sure=true,
        topcard = false,
        topsure = true,
        cut = false,
        nav = "pen_pal_invite_screen",
        sendPenPalMail = writePenPalViewModel::sendPenPalMail,
        writePenPalViewModel = writePenPalViewModel
    )
}
@ExperimentalFoundationApi
@Preview
@Composable
fun PenPreviewWriteMail1(){
    val lists = listOf(LineItem(""), LineItem(""))
    val writePenPalViewModel: WritePenPalViewModel = viewModel()

    val penPalViewModel: PenPalViewModel = viewModel()
    WritePenPalScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = {(LineItem)->{}},
        otherNickname ="笔友一号",
        card=false,
        sure=false,
        topcard = false,
        topsure = true,
        cut = false,
        nav = "pen_pal_invite_screen",
        sendPenPalMail = writePenPalViewModel::sendPenPalMail,
        writePenPalViewModel = writePenPalViewModel
    )
}
@ExperimentalFoundationApi
@Preview
@Composable
fun PenPreviewWriteMail2(){
    val lists = listOf(LineItem(""), LineItem(""))
    val writePenPalViewModel: WritePenPalViewModel = viewModel()

    val penPalViewModel: PenPalViewModel = viewModel()
    WritePenPalScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = {(LineItem)->{}},
        otherNickname ="笔友一号",
        card=false,
        sure=true,
        topcard = true,
        topsure = false,
        cut = false,
        nav = "pen_pal_invite_screen",
        sendPenPalMail = writePenPalViewModel::sendPenPalMail,
        writePenPalViewModel = writePenPalViewModel
    )
}
@ExperimentalFoundationApi
@Preview
@Composable
fun PenPreviewWriteMail3(){
    val lists = listOf(LineItem(""), LineItem(""))
    val writePenPalViewModel: WritePenPalViewModel = viewModel()

    val penPalViewModel: PenPalViewModel = viewModel()
    WritePenPalScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = {(LineItem)->{}},
        otherNickname ="笔友一号",
        card=false,
        sure=false,
        topcard = true,
        topsure = false,
        cut = true,
        nav = "pen_pal_invite_screen",
        sendPenPalMail = writePenPalViewModel::sendPenPalMail,
        writePenPalViewModel = writePenPalViewModel
    )
}
@ExperimentalFoundationApi
@Preview
@Composable
fun PenPreviewWriteMail4(){
    val lists = listOf(LineItem(""), LineItem(""))
    val writePenPalViewModel: WritePenPalViewModel = viewModel()

    val penPalViewModel: PenPalViewModel = viewModel()
    WritePenPalScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = {(LineItem)->{}},
        otherNickname ="笔友一号",
        card=false,
        sure=false,
        topcard = false,
        topsure = true,
        cut = false,
        nav = "pen_pal_invite_screen",
        sendPenPalMail = writePenPalViewModel::sendPenPalMail,
        writePenPalViewModel = writePenPalViewModel
    )
}