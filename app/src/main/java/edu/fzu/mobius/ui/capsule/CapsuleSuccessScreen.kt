package edu.fzu.mobius.ui.capsule

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.ui.common.NoShadowBottomAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.ui.mail.WriteCapsuleViewModel

@Composable
fun CapsuleSuccessScreen(
    navController: NavController,
    writeCapsuleViewModel: WriteCapsuleViewModel,
) {
    Scaffold(
        backgroundColor = Color(0xFFF0EFF3),
        topBar = {
            BaseTitleTop(
                navController = navController,
                router = "capsule_screen"
            ) },
        bottomBar = {
            NoShadowBottomAppBar(
                modifier = Modifier
                    .padding(12.dp)
                    .height(350.dp)
            ) {
                TextButton(
                    onClick = { /*TODO*/
                        navController.navigate("return_write_capsule_screen")
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
                        text = "????????????",
                        fontSize = 20.sp
                    )
                }
            }
            Text(
                text = "?????????????????????",
                modifier = Modifier

                    .padding(start = 130.dp, top = 40.dp)
                    ,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
            Text(
                text = writeCapsuleViewModel.return_mDate.value,
                modifier = Modifier
                    .padding(start = 120.dp, top = 90.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
    ) {
        ConstraintLayout() {
            val (icon,text1,text2) = createRefs()
            UnspecifiedIcon(
                painter = painterResource(id = R.drawable.plane),
                modifier = Modifier
                    .height(400.dp)
                    .width(380.dp)
                    .padding(0.dp)
                    .constrainAs(icon){
                        start.linkTo(parent.start, margin = 30.dp)
                        top.linkTo(parent.top, margin = 0.dp)
                        bottom.linkTo(parent.bottom,margin = 0.dp)
                    }
            )


        }
    }
}

@Preview
@Composable
fun CapsulePreviewInvite(){
    val writeCapsuleViewModel: WriteCapsuleViewModel = viewModel()
    CapsuleSuccessScreen(
        navController = rememberNavController(),
        writeCapsuleViewModel = writeCapsuleViewModel
    )
}