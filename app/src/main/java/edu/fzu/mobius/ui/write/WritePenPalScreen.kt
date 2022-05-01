package edu.fzu.mobius.ui.write

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
import edu.fzu.mobius.ui.common.NoShadowBottomAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.compose.mailbox.top.MailBoxTop
import edu.fzu.mobius.compose.penpal.PenOtherUser
import edu.fzu.mobius.theme.BlueBackground
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.theme.PrimaryVariant
import edu.fzu.mobius.theme.bluetext
import kotlinx.coroutines.NonDisposableHandle.parent

@ExperimentalFoundationApi
@Composable
fun WritePenPalScreen(
    navController: NavController,
    items:List<lineItem>,
    onEditItemChange: (lineItem) -> Unit,
    otherNickname: String
) {

    var cardVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            MailBoxTop(
                navController = navController,
                router = "pen_pal_screen"
            )
        },
        bottomBar = {
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
                            PenOtherUser(nickname = "黄埔铁牛",
                                modifier = Modifier.animateItemPlacement())
                        }
                    }
                    TextButton(
                        onClick = { /*TODO*/
                            cardVisible = true
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
    ) {
        ConstraintLayout() {
            val (edit, card) = createRefs()

            PenPalMailEditor(
                otherNickname = otherNickname,
                items = items,
                onEditItemChange = onEditItemChange,
                modifier = Modifier
                    .constrainAs(edit) {

                    }
            )
            AnimatedVisibility(
                visible = cardVisible,
                modifier = Modifier
                    .constrainAs(card) {
                        start.linkTo(parent.start, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                    }
                    .background(Color.White),
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
                        .padding(12.dp,0.dp)
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
                                fontSize = 18.sp,)
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
        }
    }
}

@Composable
fun PenPalMailEditor(
    otherNickname:String,
    items: List<lineItem>,
    onEditItemChange: (lineItem) -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .padding(start = 12.dp , end = 12.dp , bottom = 100.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ){
            item(){
                PenPalLineInput(
                    text = "To:$otherNickname",
                    onTextChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .width(120.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            items(items = items){
                val item = it
                PenPalLineInput(
                    text = it.value,
                    onTextChange = { onEditItemChange(item.copy(value = it)) },
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PenPalLineInput(
    text:String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    fontWeight: FontWeight = FontWeight.Normal
){
    TextField(
        value = text,
        onValueChange = onTextChange,
        readOnly = readOnly,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Unspecified,
            focusedIndicatorColor = PrimaryVariant,
            unfocusedIndicatorColor = Color(0xFFBDBDBD),
            cursorColor = Color.Blue
        ),
        textStyle = TextStyle(
            fontSize = 16.sp,
            textDecoration = TextDecoration.None,
            lineHeight = 30.sp,
            textAlign = TextAlign.Left,
            fontWeight = fontWeight
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp)
            .height(50.dp)
    )
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PenPreviewWriteMail(){
    val lists = listOf(lineItem(""), lineItem(""))
    WritePenPalScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = {(lineItem)->{}},
        otherNickname ="笔友一号"
    )
}
