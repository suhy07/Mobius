package edu.fzu.mobius.ui.mailbox

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.base.NoShadowButton
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.ui.common.nav.bottom.NavBottom
import edu.fzu.mobius.ui.common.nav.float.NavFloatButton
import edu.fzu.mobius.ui.theme.BlueBackground

@ExperimentalAnimationApi
@Composable
fun MailBoxScreen(navController: NavController){
    var cardVisible by remember { mutableStateOf(false) }
    var floatingVisible by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }
    var time by remember { mutableStateOf("21:00") }
    val items = listOf("00:00","01:00","21:00","00:00","01:00","21:00","00:00","01:00","21:00")
    Scaffold(
        backgroundColor = BlueBackground,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Unspecified,
                modifier = Modifier.shadow(0.dp),
                elevation = 0.dp
            ) {
                ConstraintLayout() {
                    val (more,sign,envelope,red) = createRefs()
                    NoShadowButton(
                        onClick = {
                            cardVisible = !cardVisible
                            floatingVisible = !floatingVisible
                            Log.d("TAGTAG","ok")
                                  },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .defaultMinSize(1.dp, 1.dp)
                            .shadow(0.dp)
                            .constrainAs(more) {
                                top.linkTo(parent.top, margin = 20.dp)
                                start.linkTo(parent.start, margin = 20.dp)
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.more_icon),
                            null,
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp)
                        )
                    }
                    NoShadowButton(
                            onClick = { /*TODO 签到*/ },
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier
                                .defaultMinSize(1.dp, 1.dp)
                                .constrainAs(sign) {
                                    top.linkTo(parent.top, margin = 20.dp)
                                    start.linkTo(parent.start, margin = 280.dp)
                                }
                            ) {
                        Image(
                            painter = painterResource(id = R.mipmap.sign_icon),
                            null,
                            modifier = Modifier
                                .height(25.dp)
                                .width(25.dp)
                        )

                    }
                    NoShadowButton(
                        onClick = { navController.navigate("my_mailbox_screen") },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .defaultMinSize(1.dp, 1.dp)
                            .constrainAs(envelope) {
                                top.linkTo(parent.top, margin = 20.dp)
                                start.linkTo(parent.start, margin = 350.dp)
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.envelope_icon),
                            null,
                            modifier = Modifier
                                .height(25.dp)
                                .width(25.dp)
                        )
                    }
                    UnspecifiedIcon(
                        painter = painterResource(id = R.mipmap.red_icon),
                        modifier = Modifier
                            .background(Color.Unspecified)
                            .height(8.dp)
                            .width(8.dp)
                            .constrainAs(red) {
                                top.linkTo(envelope.top, margin = 10.dp)
                                start.linkTo(envelope.start, margin = 32.dp)
                            }
                    )
                }
            }
        },
        bottomBar = {
            NavBottom(navController = navController, act = 0)
        } ,
        floatingActionButton = {
            AnimatedVisibility(visible =floatingVisible ) {
                NavFloatButton {
                    /* onClick */
                }
            }
        }
    ) {
        // Screen content
        ConstraintLayout {
            // Create references for the composables to constrain
            val (image,text1,text2,card) = createRefs()
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        start.linkTo(parent.start, margin = 60.dp)
                        end.linkTo(parent.end, margin = 2.dp)
                        bottom.linkTo(parent.bottom, margin = 120.dp)
                    },
                painter = painterResource(id = R.drawable.mailbox),
                contentDescription = "background_image",
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "每寄出一封信,",
                modifier = Modifier
                    .constrainAs(text1) {
                        start.linkTo(parent.start, margin = 45.dp)
                        bottom.linkTo(parent.bottom, margin = 220.dp)
                    },
            )
            Text(
                text = "你都将收获一份慰藉...",
                modifier = Modifier
                    .constrainAs(text2) {
                        start.linkTo(parent.start, margin = 45.dp)
                        bottom.linkTo(parent.bottom, margin = 180.dp)
                    }
            )
            AnimatedVisibility(
                visible = cardVisible,
                modifier= Modifier
                    .constrainAs(card) {
                        start.linkTo(parent.start, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 40.dp)
                    }
                    .background(Color.Unspecified),
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight*2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight*2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ) { // this: AnimatedVisibilityScope
                // Use AnimatedVisibilityScope#transition to add a custom animation
                // to the AnimatedVisibility.
                Card(
                    shape = RoundedCornerShape(topStart = 20.dp,topEnd = 20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .height(200.dp)
                        .background(Color.Unspecified)
                ){
                    ConstraintLayout() {
                        val (close,clock,text, dropButton) = createRefs()
                        NoShadowButton(
                            onClick = {
                                cardVisible = false
                                floatingVisible = true
                            },
                            modifier = Modifier
                                .constrainAs(close) {
                                    end.linkTo(parent.end, margin = 10.dp)
                                    top.linkTo(parent.top, margin = 10.dp)
                                }
                        ) {
                            UnspecifiedIcon(
                                painter = painterResource(id = R.mipmap.close_icon),
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                            )
                        }
                        UnspecifiedIcon(
                            painter = painterResource(id = R.mipmap.clock_icon),
                            modifier = Modifier
                                .constrainAs(clock) {
                                    start.linkTo(parent.start, margin = 0.dp)
                                    bottom.linkTo(parent.bottom, margin = 90.dp)
                                }
                                .fillMaxWidth()
                                .height(90.dp)
                        )
                        Text(
                            text = "收信时间",
                            modifier = Modifier
                                .fillMaxWidth()
                                .constrainAs(text) {
                                    start.linkTo(parent.start, margin = 0.dp)
                                    bottom.linkTo(parent.bottom, margin = 60.dp)
                                },
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                        TextButton(
                            onClick = { expanded = true },
                            modifier = Modifier
                                .constrainAs(dropButton) {
                                    start.linkTo(parent.start, margin = 0.dp)
                                    bottom.linkTo(parent.bottom, margin = 0.dp)
                                    top.linkTo(parent.top,margin = 120.dp)
                                    end.linkTo(parent.end,margin = 0.dp)
                                }
                        ) {
                            Text(
                                text = "    每晚"+time+"  ∨",
                                textAlign = TextAlign.Center
                            )
                            DropdownMenu(
                                expanded = expanded ,
                                onDismissRequest = { expanded = false},
                                properties = PopupProperties(focusable = false),
                                modifier = Modifier
                                    .height(50.dp)
                            ) {

                                items.forEachIndexed { _, s ->
                                    DropdownMenuItem(onClick = {
                                        expanded = false
                                        time = s
                                    }) {
                                        Text(text = "$s        -")
                                    }
                                }

                            }
                        }

                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewMailBox() {
    val navController = rememberNavController()
    MailBoxScreen(navController = navController)
}
