package edu.fzu.mobius.ui.penpal

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.compose.mailbox.top.MailBoxTop
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.ui.common.NoShadowBottomAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon

@Composable
fun InviteSuccessScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            MailBoxTop(
                navController = navController,
                router = "pen_pal_screen"
            ) },
        bottomBar = {
            NoShadowBottomAppBar(
                modifier = Modifier
                    .padding(12.dp)
                    .height(500.dp)
            ) {
                TextButton(
                    onClick = { /*TODO*/
                        navController.navigate("return_write_pen_pal_screen")
                              },
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.elevation(10.dp, 10.dp, 10.dp),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = BlueButton,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .width(250.dp)
                        .fillMaxWidth()
                        .padding(start = 100.dp, top = 0.dp)
                ) {
                    Text(
                        text = "查看信件",
                        fontSize = 20.sp
                    )
                }
            }
        }
    ) {
        ConstraintLayout() {
            val (icon,edit) = createRefs()
            UnspecifiedIcon(
                painter = painterResource(id = R.drawable.letter),
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
                    .padding(0.dp)
                    .constrainAs(icon){
                        start.linkTo(parent.start, margin = 50.dp)
                        top.linkTo(parent.top, margin = 100.dp)
                    }
            )

        }
    }
}
@Preview
@Composable
fun PreviewInvite(){
    InviteSuccessScreen(
        navController = rememberNavController(),
    )
}