package edu.fzu.mobius.ui.common.mailbox.top

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import edu.fzu.mobius.ui.common.NoShadowTopAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon

@Composable
fun MailBoxTop(navController: NavController,router: String,title: String = "") {
    NoShadowTopAppBar(
    ) {
        ConstraintLayout() {
            val (back,title_) = createRefs()
            NoShadowButton(
                onClick = {
                    navController.navigate(route = router) {
                        popUpTo(router) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .constrainAs(back) {
                        top.linkTo(parent.top, margin = 0.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                    }
            ) {
                UnspecifiedIcon(
                    painter = painterResource(id = R.mipmap.back_icon),
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

@Preview
@Composable
fun PreviewMailBoxTop(){
    MailBoxTop(navController = rememberNavController(), router = "",title = "我的信箱")

}