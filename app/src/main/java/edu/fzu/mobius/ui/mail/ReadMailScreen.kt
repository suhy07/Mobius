package edu.fzu.mobius.ui.mail

import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.ButtonBottom
import edu.fzu.mobius.compose.MailEditor
import edu.fzu.mobius.ui.common.UnspecifiedIcon

@Composable
fun ReadMailScreen(
    otherNickname: String,
    icon: String,
    content: String,
    flower: (NavController)->Unit,
    reply: (NavController)->Unit,
    items:List<LineItem>,
    onEditItemChange: (LineItem) -> Unit,
    navController: NavController
) {
    Scaffold(
        topBar = {
            ConstraintLayout {
                val (report) = createRefs()
                UnspecifiedIcon(
                    painter = painterResource(id = R.mipmap.report_icon),
                    modifier = Modifier
                        .height(30.dp)
                        .constrainAs(report) {
                            end.linkTo(parent.end, margin = 20.dp)
                            top.linkTo(parent.top, margin = 0.dp)
                        }
                )
            }
            BaseTitleTop(
                navController = navController,
                router = "anon_mailbox_screen"
            ) },
        bottomBar = {
//            AnimatedVisibility(
//                visible = cardVisible,
//                modifier = Modifier
//
//                    .background(Color.Unspecified),
//                enter = slideInVertically(
//                    initialOffsetY = { fullHeight -> fullHeight * 2 },
//                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
//                ),
//                exit = slideOutVertically(
//                    targetOffsetY = { fullHeight -> fullHeight * 2 },
//                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
//                ),
//            ) { // this: AnimatedVisibilityScope
//                // Use AnimatedVisibilityScope#transition to add a custom animation
//                // to the AnimatedVisibility.
//                Card(
//                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(350.dp)
//                        .background(Color.Unspecified)
//                ) {
//                    ConstraintLayout() {
//                        val (cardtext, slider,row, cardtext1, cardbutton) = createRefs()
//                        val progress = remember { mutableStateOf(0f) }
//                        Text(
//                            text = "设置忧郁值"+"   "+(progress.value*100).toInt()+"%",
//                            modifier = Modifier
//                                .constrainAs(cardtext) {
//                                    start.linkTo(parent.start, margin = 15.dp)
//                                    top.linkTo(parent.top, margin = 25.dp)
//                                },
//                            fontSize = 16.sp,)
//                        Slider(
//                            value = progress.value,
//                            onValueChange = {
//                                progress.value = it
//                            },
//                            colors = SliderDefaults.colors(
//                                thumbColor = Color.Blue,
//                                inactiveTrackColor = Color.LightGray,
//                                activeTrackColor = Color.Blue
//                            ),
//                            modifier = Modifier
//                                .constrainAs(slider) {
//                                    start.linkTo(parent.start, margin = 5.dp)
//                                    top.linkTo(parent.top, margin = 55.dp)
//                                },
//                        )
//                        Row(
//                            modifier = Modifier
//                                .constrainAs(row) {
//                                    start.linkTo(parent.start, margin = 15.dp)
//                                    top.linkTo(parent.top, margin = 105.dp) },
//                        )
//                        {
//                            Text(
//                                modifier = Modifier
//                                    .padding(start = 0.dp,top = 10.dp)
//                                ,
//                                text = "设置收信时间  ",
//                                fontSize = 14.sp,
//                            )
//                            Button(
//                                modifier = Modifier
//                                    .width(200.dp)
//                                    .height(35.dp)
//                                ,
//                                colors = ButtonDefaults.buttonColors(
//                                    backgroundColor = Color.White,
//                                ),
//                                onClick = {
//                                    mDatePickerDialog.show()}
//                            ){
//                                Icon(painter = painterResource(id = R.drawable.calendar), null)
//                                Text(text = mDate.value)
//                            }
//                        }
//                        Text(
//                            text = "每次邀请使用一张邮票",
//                            modifier = Modifier
//                                .constrainAs(cardtext1) {
//                                    start.linkTo(parent.start, margin = 15.dp)
//                                    top.linkTo(parent.top, margin = 165.dp)
//                                },
//                            fontSize = 14.sp,
//                            color = bluetext,)
//                        TextButton(
//                            onClick = { /*TODO 发送成功*/
//                                arriveTime.value = mDate.value
////                                sendWriteCapsule(navController)
//                                navController.navigate("capsule_success_screen")
//                            },
//                            shape = RoundedCornerShape(20.dp),
//                            elevation = ButtonDefaults.elevation(10.dp, 10.dp, 10.dp),
//                            colors = ButtonDefaults.textButtonColors(
//                                backgroundColor = BlueButton,
//                                contentColor = Color.White
//                            ),
//                            modifier = Modifier
//                                .height(60.dp)
//                                .width(365.dp)
//                                .constrainAs(cardbutton) {
//                                    start.linkTo(parent.start, margin = 25.dp)
//                                    top.linkTo(parent.top, margin = 205.dp)
//                                },
//                        ) {
//                            Text(
//                                text = "确定",
//                                fontSize = 20.sp
//                            )
//                        }
//                    }
//                }
//            }
            ButtonBottom(
                onClick = { /*TODO*/ },
                title = "发送信件"
            )
        }
    ) {
        ConstraintLayout {
            val (report,edit) = createRefs()
            MailEditor(
                otherNickname = otherNickname,
                items = items,
                onEditItemChange = onEditItemChange,
                modifier = Modifier
                    .constrainAs(edit){

                    },
            )
        }
    }
}

@Preview
@Composable
fun PreviewRead(){
    val navController = rememberNavController()
    ReadMailScreen(
        otherNickname = "",
        icon = "",
        content = "",
        flower = {},
        reply = {},
        items = listOf(),
        onEditItemChange = {},
        navController = navController
    )
}