package edu.fzu.mobius.ui.mail


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.ButtonBottom
import edu.fzu.mobius.compose.MailEditor
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.ui.anon.WriteAnonViewModel
import edu.fzu.mobius.ui.common.UnspecifiedIcon

@SuppressLint("RememberReturnType")
@ExperimentalMaterialApi
@Composable
fun WriteAnonMailScreen(
    navController: NavController,
    otherNickname: String
) {
    val writeMailViewModel: WriteMailViewModel = viewModel()
    val cardVisible = remember { mutableStateOf(false) }
    val items = writeMailViewModel.LineItems
    val onEditItemChange = writeMailViewModel::onEditItemChange
    val writeAnonViewModel: WriteAnonViewModel = viewModel()
    Scaffold(
        topBar = {
            BaseTitleTop(
                navController = navController,
                router = "mailbox_screen"
            ) },
        bottomBar = {
            ButtonBottom(
                onClick = {
                    cardVisible.value = true
                },
                title = "发送信件"
            )
            AnimatedVisibility(
                visible = cardVisible.value,
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
                Card(
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Unspecified)
                ) {
                    ConstraintLayout {
                        val (cardText, slider, cardButton) = createRefs()
                        val progress = remember { mutableStateOf(0f) }
                        Text(
                            text = "设置忧郁值"+"   "+(progress.value*100).toInt()+"%",
                            modifier = Modifier
                                .constrainAs(cardText) {
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
                        ButtonBottom(
                            onClick = {
                                cardVisible.value = true
                                writeAnonViewModel.sentAnon(
                                    content = writeMailViewModel.letterValue.value,
                                    mood = progress.value,
                                    navController = navController
                                )
                            },
                            title = "确定",
                            modifier = Modifier
                                .constrainAs(cardButton){
                                    bottom.linkTo(parent.bottom)
                                }
                        )
                    }
                }
            }
        }
    ) {
        ConstraintLayout {
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
    WriteAnonMailScreen(
        navController = rememberNavController(),
        otherNickname ="陌生人"
    )
}


