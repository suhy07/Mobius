package edu.fzu.mobius.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.base.NoShadowButton
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.ui.common.NoShadowBottomAppBar
import edu.fzu.mobius.ui.common.NoShadowTopAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon

@Composable
fun BaseTitleTop(navController: NavController, router: String, title: String = "") {
    NoShadowTopAppBar {
        ConstraintLayout {
            val (back,title_) = createRefs()
            NoShadowButton(
                onClick = {
                    singleTaskNav(navController,router)
                },
                modifier = Modifier
                    .constrainAs(back) {
                        top.linkTo(parent.top, margin = 0.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                    }
            ) {
                UnspecifiedIcon(
                    painter = painterResource(id = R.mipmap.back_left_icon),
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .padding(start = 20.dp)
                )
            }
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(title_) {
                        top.linkTo(parent.top, margin = 0.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                    },
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}

@Composable
fun ButtonBottom(
    onClick: ()->Unit,
    title: String
){
    NoShadowBottomAppBar(
        modifier = Modifier
            .padding(12.dp)
            .height(100.dp)
    ) {
        TextButton(
            onClick = onClick,
            shape = RoundedCornerShape(20.dp),
            elevation = ButtonDefaults.elevation(10.dp, 10.dp, 10.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = BlueButton,
                contentColor = Color.White
            ),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 20.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewMailBoxTop(){
    BaseTitleTop(
        navController = rememberNavController(),
        router = "",
        title = "我的信箱"
    )
}