package edu.fzu.mobius.ui.mailbox

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.base.NoShadowButton
import edu.fzu.mobius.compose.CloseButton
import edu.fzu.mobius.global.GlobalMem
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.ui.common.NoShadowTopAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.ui.common.nav.bottom.NavBottom
import edu.fzu.mobius.ui.common.nav.float.NavFloatButton
import edu.fzu.mobius.theme.BlueBackground
import edu.fzu.mobius.ui.mine.MineViewModel
import edu.fzu.mobius.ui.signin.SignInPop

@ExperimentalAnimationApi
@Composable
fun MailBoxScreen(navController: NavController){
    val mailBoxViewModel: MailBoxViewModel = viewModel()
    var cardVisible = mailBoxViewModel.cardVisible
    var signInVisible = mailBoxViewModel.signInVisible
    var floatingVisible = mailBoxViewModel.floatingVisible
    Scaffold(
        backgroundColor = BlueBackground,
        topBar = {
            NoShadowTopAppBar {
                ConstraintLayout {
                    val (more,sign,envelope,red) = createRefs()
                    NoShadowButton(
                        onClick = {
                            cardVisible.value = !cardVisible.value
                            floatingVisible.value = !floatingVisible.value
                            signInVisible.value = false
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
                                .height(25.dp)
                                .width(30.dp)
                        )
                    }
                    NoShadowButton(
                            onClick = {
                                signInVisible.value = !signInVisible.value
                            },
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier
                                .defaultMinSize(1.dp, 1.dp)
                                .constrainAs(sign) {
                                    top.linkTo(parent.top, margin = 20.dp)
                                    start.linkTo(parent.start, margin = 260.dp)
                                }
                            ) {
                        Image(
                            painter = painterResource(id = R.mipmap.sign_icon),
                            null,
                            modifier = Modifier
                                .height(35.dp)
                                .width(30.dp)
                        )

                    }
                    NoShadowButton(
                        onClick = { navController.navigate("my_mailbox_screen") },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .defaultMinSize(1.dp, 1.dp)
                            .constrainAs(envelope) {
                                top.linkTo(parent.top, margin = 20.dp)
                                start.linkTo(parent.start, margin = 330.dp)
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.envelope_icon),
                            null,
                            modifier = Modifier
                                .height(20.dp)
                                .width(30.dp)
                        )
                    }
                    UnspecifiedIcon(
                        painter = painterResource(id = R.mipmap.red_icon),
                        modifier = Modifier
                            .background(Color.Unspecified)
                            .height(10.dp)
                            .width(10.dp)
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
                        navController.navigate("write_anon_mail_screen")
                    }
                )
            }
        }
    ) {
        // Screen content
        ConstraintLayout {
            // Create references for the composables to constrain
            val (image,text1,text2,card,button,signIn) = createRefs()
            NoShadowButton(
                onClick = {
                    singleTaskNav(navController,"anon_mailbox_screen")
                },
                modifier = Modifier
                    .width(150.dp)
                    .height(90.dp)
                    .constrainAs(button) {
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 60.dp)
                        bottom.linkTo(parent.bottom, margin = 320.dp)
                        top.linkTo(parent.top, margin = 0.dp)
                    },
            ){}
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
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(text1) {
                        start.linkTo(parent.start, margin = 45.dp)
                        bottom.linkTo(parent.bottom, margin = 220.dp)
                    },
            )
            Text(
                text = "你都将收获一份慰藉...",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(text2) {
                        start.linkTo(parent.start, margin = 45.dp)
                        bottom.linkTo(parent.bottom, margin = 180.dp)
                    }
            )
            AnimatedVisibility(
                visible = cardVisible.value,
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
            ) {
                SetTimePop()
            }
            AnimatedVisibility(
                visible = signInVisible.value,
                modifier= Modifier
                    .constrainAs(signIn) {
                        start.linkTo(parent.start, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 160.dp)
                        top.linkTo(parent.top, margin = 0.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                    }
                    .background(Color.Unspecified),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                SignInPop()
            }
        }
    }
}

@Composable
fun SetTimePop(
){
    val mailBoxViewModel: MailBoxViewModel = viewModel()
    var cardVisible = mailBoxViewModel.cardVisible
    var floatingVisible = mailBoxViewModel.floatingVisible
    var expanded = mailBoxViewModel.expanded
    val items = GlobalMem.ANON_TIME_LIST

    Card(
        shape = RoundedCornerShape(topStart = 20.dp,topEnd = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(200.dp)
            .background(Color.Unspecified)
    ) {
        ConstraintLayout() {
            val (close, clock, text, dropButton) = createRefs()
            CloseButton(
                onClick = {
                    cardVisible.value = false
                    floatingVisible.value = true
                },
                modifier = Modifier
                    .constrainAs(close) {
                        end.linkTo(parent.end, margin = 10.dp)
                        top.linkTo(parent.top, margin = 10.dp)
                    }
            )
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
                onClick = { expanded.value = true },
                modifier = Modifier
                    .constrainAs(dropButton) {
                        start.linkTo(parent.start, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                        top.linkTo(parent.top, margin = 120.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                    }
            ) {
                Text(
                    text = "    每天" + GlobalMem.ANON_TIME + "  ∨",
                    textAlign = TextAlign.Center
                )
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                    properties = PopupProperties(focusable = false),
                    modifier = Modifier
                        .height(50.dp)
                ) {

                    items.forEachIndexed { _, s ->
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            GlobalMem.ANON_TIME = s
                        }) {
                            Text(text = "$s        -")
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

@Preview
@Composable
fun PreviewSetTimePop() {
    SetTimePop()
}