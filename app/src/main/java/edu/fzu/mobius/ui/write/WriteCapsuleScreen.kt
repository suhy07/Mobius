package edu.fzu.mobius.ui.write

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.compose.MailEditor
import edu.fzu.mobius.compose.mailbox.top.MailBoxTop
import edu.fzu.mobius.compose.penpal.PenOtherUser
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.theme.bluetext
import edu.fzu.mobius.ui.common.NoShadowBottomAppBar


@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun WriteCapsuleScreen(
    navController: NavController,
    items:List<lineItem>,
    onEditItemChange: (lineItem) -> Unit,
    otherNickname: String
) {

    val isClick = rememberSaveable  { mutableStateOf(false) }
    var selectList: MutableList<String> = mutableListOf("我自己","好友1","好友2","好友3","好友4")
    val selectType = rememberSaveable { mutableStateOf(selectList[0])}
    var cardVisible by remember { mutableStateOf(false) }
    var sureVisible by remember { mutableStateOf(true) }
    var openDialog by remember { mutableStateOf(false) }
    var Time by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            MailBoxTop(
                navController = navController,
                router = "capsule_screen"
            )
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
                            text = "发送信件",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }}
    ) {
        ConstraintLayout() {
            val (edit, card,card1) = createRefs()
            MailEditor(
                otherNickname = otherNickname,
                items = items,
                onEditItemChange = onEditItemChange,
                modifier = Modifier
                    .constrainAs(edit) {
                    },
                enable = true,
                onClick = {
                    isClick.value=!isClick.value
                    Log.d("ASD","SDDS")
                },
            )
            DropdownMenu(
                expanded = isClick.value,
                modifier = Modifier.fillMaxWidth(),
                onDismissRequest = {},
                content = {
                    selectList.forEach {
                        DropdownMenuItem(
                            onClick = {
                                isClick.value = !isClick.value
                                selectType.value = it
                            },
                            content = {
                                Text(text = it)
                            }
                        )
                    }
                }
            )
            AnimatedVisibility(
                visible = cardVisible,
                modifier = Modifier
                    .constrainAs(card) {
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                    }
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
                        .height(350.dp)
                        .background(Color.Unspecified)
                ) {
                    ConstraintLayout() {
                        val (cardtext, slider,row, cardtext1, cardbutton) = createRefs()
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
                        Row(
                            modifier = Modifier
                                .constrainAs(row) {
                                    start.linkTo(parent.start, margin = 15.dp)
                                    top.linkTo(parent.top, margin = 105.dp) },
                        )
                        {
                            Text(
                                modifier = Modifier
                                .padding(start = 0.dp,top = 10.dp)
                                ,
                                text = "设置收信时间  ",
                                fontSize = 14.sp,
                            )
                            Button(
                                modifier = Modifier
                                    .width(200.dp)
                                ,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.White,
                                ),
                                onClick = { openDialog = true}
                            ){
                                Icon(painter = painterResource(id = R.drawable.calendar), null)
                                Text(text = Time)
                            }
                        }
                        Text(
                            text = "每次邀请使用一张邮票",
                            modifier = Modifier
                                .constrainAs(cardtext1) {
                                    start.linkTo(parent.start, margin = 15.dp)
                                    top.linkTo(parent.top, margin = 165.dp)
                                },
                            fontSize = 14.sp,
                            color = bluetext,)
                        TextButton(
                            onClick = { /*TODO 发送成功*/

                                navController.navigate("pen_pal_invite_screen")
                            },
                            shape = RoundedCornerShape(20.dp),
                            elevation = ButtonDefaults.elevation(10.dp, 10.dp, 10.dp),
                            colors = ButtonDefaults.textButtonColors(
                                backgroundColor = BlueButton,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .height(60.dp)
                                .width(300.dp)
                                .constrainAs(cardbutton) {
                                    start.linkTo(parent.start, margin = 15.dp)
                                    top.linkTo(parent.top, margin = 205.dp)
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
//            AnimatedVisibility(
//                visible = openDialog,
//                modifier = Modifier
//                    .constrainAs(card1) {
//                        bottom.linkTo(parent.bottom, margin = 100.dp)
//                    }
//                    .background(Color.Unspecified),
//                enter = slideInVertically(
//                    initialOffsetY = { fullHeight -> fullHeight * 2 },
//                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
//                ),
//                exit = slideOutVertically(
//                    targetOffsetY = { fullHeight -> fullHeight * 2 },
//                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
//                ),
//            ){
//                DatePickerDialog(true,onDateSelected = {year,month,dayofMonth ->
//                    Time = "1";
//                },)
//            }

        }
    }
}


@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
fun CapsulePreviewWriteMail(){
    val lists = listOf(lineItem(""), lineItem(""))
    WriteCapsuleScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = {(lineItem)->{}},
        otherNickname =""
    )
}
