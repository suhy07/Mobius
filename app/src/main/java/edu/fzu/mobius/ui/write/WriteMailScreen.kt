package edu.fzu.mobius.ui.write


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.ButtonBottom
import edu.fzu.mobius.compose.MailEditor
import edu.fzu.mobius.ui.common.NoShadowBottomAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.theme.PrimaryVariant

@ExperimentalMaterialApi
@Composable
fun WriteMailScreen(
    navController: NavController,
    items:List<lineItem>,
    onEditItemChange: (lineItem) -> Unit,
    otherNickname: String
) {
    Scaffold(
        topBar = {
            BaseTitleTop(
                navController = navController,
                router = "mailbox_screen"
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
        ConstraintLayout() {
            val (icon,edit) = createRefs()
            UnspecifiedIcon(
                painter = painterResource(id = R.drawable.letter1),
                modifier = Modifier
                    .height(60.dp)
                    .constrainAs(icon) {
                        end.linkTo(parent.end, margin = 20.dp)
                        top.linkTo(parent.top, margin = 0.dp)
                    }
            )
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



@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewWriteMail(){
    val lists = listOf(lineItem(""), lineItem(""))
    WriteMailScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = {(lineItem)-> run {} },
        otherNickname ="陌生人"
    )
}


