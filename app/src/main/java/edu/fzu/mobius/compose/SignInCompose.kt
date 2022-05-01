package edu.fzu.mobius.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.fzu.mobius.R
import edu.fzu.mobius.base.NoShadowButton
import edu.fzu.mobius.ui.common.UnspecifiedIcon

/*
  gift:礼物内容
  state:是否已开启  true未开启，false已开启
*/
@Composable
fun SignInItemScreen(
    gift:String,
    state:Boolean,
    enable:Boolean = false,
    modifier: Modifier = Modifier
) {
    var _state by remember { mutableStateOf(state) }
    var _enable by remember { mutableStateOf(enable) }
    NoShadowButton(
        onClick = {
            _state = !_state
            _enable = false
        },
        enabled = _enable && _state,
        modifier = modifier
            .height(100.dp)
            .width(90.dp)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Column() {
            AnimatedVisibility(
                visible = _state,
                exit = fadeOut()
            ) {
                UnspecifiedIcon(
                    painter = painterResource(id = R.mipmap.gift_icon),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            AnimatedVisibility(
                visible = !_state,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight*2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
            ) {
                Column() {
                    UnspecifiedIcon(
                        painter = painterResource(id = R.mipmap.gift_invalid_icon),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = gift,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun SignInBigItemScreen(
    gift:String,
    state:Boolean,
    enable:Boolean = false,
    modifier: Modifier = Modifier
) {
    var _state by remember { mutableStateOf(state) }
    var _enable by remember { mutableStateOf(enable) }
    NoShadowButton(
        onClick = {
            _state = !_state
            _enable = false
        },
        enabled = _enable && _state,
        modifier = modifier
            .height(180.dp)
            .width(160.dp)
            .padding(all = 0.dp)
    ) {
        Column() {
            AnimatedVisibility(
                visible = _state,
                exit = fadeOut()
            ) {
                UnspecifiedIcon(
                    painter = painterResource(id = R.mipmap.gift_big_icon),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            AnimatedVisibility(
                visible = !_state,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight*2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
            ) {
                Column() {
                    UnspecifiedIcon(
                        painter = painterResource(id = R.mipmap.gift_big_invalid_icon),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = gift,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignInItem(){
    SignInItemScreen("5点成长值",true)
}

@Preview
@Composable
fun PreviewSignInItemInvalid(){
    SignInItemScreen("5点成长值",false)
}


@Preview
@Composable
fun PreviewSignInBigItem(){
    SignInBigItemScreen("5点成长值",true)
}

@Preview
@Composable
fun PreviewSignInBigItemInvalid(){
    SignInBigItemScreen("5点成长值",false)
}