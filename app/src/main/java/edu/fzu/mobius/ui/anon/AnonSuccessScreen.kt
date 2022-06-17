package edu.fzu.mobius.ui.anon

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.base.NoShadowButton
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.ButtonBottom
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.theme.BlueBackground
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.theme.BlueText
import edu.fzu.mobius.ui.common.UnspecifiedIcon

@Composable
fun AnonSuccessScreen(
    navController: NavController
) {
    Scaffold(
        backgroundColor = BlueBackground,
        topBar = {
        }
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (image, text, button) = createRefs()
            UnspecifiedIcon(
                painter = painterResource(id = R.drawable.letter),
                modifier = Modifier
                    .size(350.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top, margin = 0.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 200.dp)
                    }
            )
            Text(
                text = "信件发送成功",
                color = BlueText,
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 18.sp,
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(parent.top, margin = 200.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                    }

            )
            ButtonBottom(
                onClick = { singleTaskNav(navController, "mailbox_screen") },
                title = "返回主页",
                modifier = Modifier
                    .width(200.dp)
                    .height(150.dp)
                    .constrainAs(button) {
                        top.linkTo(parent.top, margin = 550.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                    }
            )
        }
    }
}

@Preview
@Composable
fun PreviewSuccess() {
    AnonSuccessScreen(navController = rememberNavController())
}