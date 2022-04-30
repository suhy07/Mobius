package edu.fzu.mobius.ui.capsule

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.ui.common.nav.bottom.NavBottom
import edu.fzu.mobius.ui.common.nav.float.NavFloatButton
import edu.fzu.mobius.theme.BlueBackground

@Composable
fun CapsuleScreen(navController: NavController){
    var floatingVisible by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            },
        backgroundColor = BlueBackground,
        bottomBar = {
            NavBottom(navController = navController, act = 2)
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
            val (image,text1,text2,text3,image2) = createRefs()

            Image(
                modifier = Modifier
//                        .fillMaxSize() 这个是直接设置宽度和高度填充parent MaxHeight&&MaxWidth
//                        .fillMaxHeight() MaxHeight
//                        .fillMaxWidth() MaxWidth
                    .constrainAs(image) {
                        start.linkTo(parent.start, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                    }
                    .height(820.dp)
                    .width(1020.dp),
                painter = painterResource(id = R.drawable.global),
                contentDescription = "background_image",
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "每寄出一封信",
                modifier = Modifier
                    .constrainAs(text1) {
                        start.linkTo(parent.start, margin = 80.dp)
                        bottom.linkTo(parent.bottom, margin = 150.dp)
                    },
            )
            Text(
                text = "都在对未来说嗨..",
                modifier = Modifier
                    .constrainAs(text2) {
                        start.linkTo(parent.start, margin = 80.dp)
                        bottom.linkTo(parent.bottom, margin = 120.dp)
                    }
            )

        }
    }
}

@Preview
@Composable
fun PreviewCapsule() {
    val navController = rememberNavController()
    CapsuleScreen(navController = navController)
}