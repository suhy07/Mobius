package edu.fzu.mobius.ui.common.nav.bottom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.base.NoShadowButton


@Composable
fun NavButton(navController:NavController, router:String, icon: Int, name:String, modifier: Modifier){
    NoShadowButton(
        onClick = {
            navController.navigate(router){
                popUpTo(router){ inclusive = true }
                launchSingleTop = true
            }
        },
        modifier = modifier
            .background(Color.Unspecified)
            .defaultMinSize(1.dp, 1.dp)
            .padding(10.dp)
            ,
        border = BorderStroke(1.dp,color = Color.Unspecified),
        contentPadding = PaddingValues( start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
    ) {
        Column() {
            Icon(
                painter = painterResource(icon),
                null ,
                tint = Color.Unspecified,
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
                    .padding(0.dp)
            )
            Text(
                text = name,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(30.dp)
                    .padding(0.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    NavButton(
        navController =navController ,
        router = "mine_screen" ,
        icon = R.mipmap.user_icon,
        name = "我的",
        modifier = Modifier
    )
}