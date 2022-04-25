package edu.fzu.mobius.ui.mailbox

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.solver.LinearSystem.DEBUG
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.ui.common.nav.bottom.NavBottom
import edu.fzu.mobius.ui.common.nav.bottom.NavButton
import edu.fzu.mobius.ui.common.nav.float.NavFloatButton
import edu.fzu.mobius.ui.theme.BlueBackground

@Composable
fun MailBoxScreen(navController: NavController){
    Scaffold(
        backgroundColor = BlueBackground,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Unspecified,
                modifier = Modifier.shadow(0.dp)
            ) {
                ConstraintLayout() {
                    val (more,sign,envelope) = createRefs()
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .defaultMinSize(1.dp, 1.dp)
                            .shadow(0.dp)
                            .constrainAs(more) {
                                top.linkTo(parent.top, margin = 0.dp)
                                start.linkTo(parent.start, margin = 0.dp)
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.more_icon),
                            null,
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp)
                        )
                    }
                    Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .defaultMinSize(1.dp, 1.dp)
                                .constrainAs(sign) {
                                    top.linkTo(parent.top, margin = 0.dp)
                                    start.linkTo(parent.start, margin = 250.dp)
                                }
                            ) {
                        Image(
                            painter = painterResource(id = R.mipmap.sign_icon),
                            null,
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp)
                        )

                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .defaultMinSize(1.dp, 1.dp)
                            .constrainAs(envelope) {
                                top.linkTo(parent.top, margin = 0.dp)
                                start.linkTo(parent.start, margin = 320.dp)
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.envelope_icon),
                            null,
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp)
                        )
                    }
                }
            }
        },
        bottomBar = {
            NavBottom(navController = navController, act = 0)
        } ,
        floatingActionButton = {
            NavFloatButton {
                /* onClick */
            }
        }
    ) {
        // Screen content
        ConstraintLayout {
            // Create references for the composables to constrain
            val (image,text1,text2) = createRefs()
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        start.linkTo(parent.start, margin = 60.dp)
                        end.linkTo(parent.end, margin = 2.dp)
                        bottom.linkTo(parent.bottom, margin = 120.dp)
                    },
                painter = painterResource(id = R.drawable.mailbox),
                contentDescription = "background_image",
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "每寄出一封信,",
                modifier = Modifier
                    .constrainAs(text1) {
                        start.linkTo(parent.start, margin = 45.dp)
                        bottom.linkTo(parent.bottom, margin = 220.dp)
                    },
            )
            Text(
                text = "你都将收获一份慰藉...",
                modifier = Modifier
                    .constrainAs(text2) {
                        start.linkTo(parent.start, margin = 45.dp)
                        bottom.linkTo(parent.bottom, margin = 180.dp)
                    },

            )
        }

    }
}

@Preview
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    MailBoxScreen(navController = navController)
}
