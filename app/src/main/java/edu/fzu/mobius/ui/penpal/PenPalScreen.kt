package edu.fzu.mobius.ui.penpal

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.base.NoShadowButton
import edu.fzu.mobius.ui.common.NoShadowTopAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.ui.common.nav.bottom.NavBottom
import edu.fzu.mobius.ui.common.nav.float.NavFloatButton
import edu.fzu.mobius.ui.theme.BlueBackground

@Composable
fun PenPalScreen(navController: NavController){
    Scaffold(
        topBar = {
            NoShadowTopAppBar (
                modifier = Modifier
                    .height(80.dp)//TopBar的高度   相关属性都可以用modifier改
                    ){
//                ConstraintLayout {
//                    //如果这个按钮的位置不太好对齐 = =，你可以套个约束布局
//                }
                NoShadowButton(
                    onClick = { /* TODO 弹出笔友选项卡 */},

                    modifier = Modifier
                        .height(180.dp)
                        .width(80.dp)
                        .padding(start = 0.dp,top = 0.dp)
                ){
                    UnspecifiedIcon(
                        painter = painterResource(R.mipmap.anony_icon)
                    )
                }
            }},
        backgroundColor = BlueBackground,
        bottomBar = {
            NavBottom(navController = navController, act = 1)
        } ,
    ) {
        // Screen content
        ConstraintLayout {
                // Create references for the composables to constrain
                val (image,text1,text2,text3,image2) = createRefs()

                Image(
                    modifier = Modifier
//                        .fillMaxSize() 这个是直接设置宽度和高度填充parent MaxHeight&&MaxWidth
//                        .fillMaxHeight() MaxHeight
//                        .fillMaxWidth() MaxWidth
                        .constrainAs(image) {
                            bottom.linkTo(parent.bottom, margin = 0.dp)
                        }
                        .height(820.dp)
                        .width(820.dp),
                    painter = painterResource(id = R.drawable.pigeon),
                    contentDescription = "background_image",
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = "有一封信正在赶来...",
                    modifier = Modifier
                        .constrainAs(text1) {
                            start.linkTo(parent.start, margin = 80.dp)
                            bottom.linkTo(parent.bottom, margin = 720.dp)
                        },
                )
                Text(
                    text = "路上遇到了暴雨.",
                    modifier = Modifier
                        .constrainAs(text2) {
                            start.linkTo(parent.start, margin = 80.dp)
                            bottom.linkTo(parent.bottom, margin = 690.dp)
                        }
                )
                Text(
                    text = "大约八小时后到达",
                    modifier = Modifier
                       .constrainAs(text3) {
                            start.linkTo(parent.start, margin = 80.dp)
                            bottom.linkTo(parent.bottom, margin = 660.dp)
                        }
                )
                Image(
                    modifier = Modifier
//                            .fillMaxSize() 这个是直接设置宽度和高度填充parent MaxHeight&&MaxWidth

                        .constrainAs(image2) {
                            start.linkTo(parent.start, margin = 260.dp)
                            bottom.linkTo(parent.bottom, margin = 640.dp)
                        }
                        .height(190.dp)
                        .width(120.dp),
                    painter = painterResource(id = R.drawable.letter2),
                    contentDescription = "letter_image",
                    contentScale = ContentScale.FillWidth
                )
        }
    }
}

@Preview
@Composable
fun PreviewPenPal() {
    val navController = rememberNavController()
    PenPalScreen(navController = navController)
}