package edu.fzu.mobius.ui.mailbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.ui.theme.BlueBackground

@Composable
fun MailBoxScreen(navController: NavController){
    Scaffold(
        backgroundColor = BlueBackground,
        topBar = {
            TopAppBar { /* Top app bar content */ }
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.White,
                modifier = Modifier
                    .padding(40.dp,0.dp)
                    .height(80.dp)
            ) {
                Row() {
                    Button(
                        onClick = {
                            navController.navigate("mailbox_screen")
                        },
                        Modifier
                            .background(Color.Unspecified)
                            .defaultMinSize(1.dp,1.dp)
                    ) {
                        Column() {
                            Icon(
                                painter = painterResource(id = R.mipmap.mailbox_act_icon),
                                null ,
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .height(25.dp)
                                    .padding(5.dp,0.dp)
                            )
                            Text(text = "信箱")
                        }
                    }
                    Button(
                        onClick = {
                            navController.navigate("mailbox_screen")
                        }
                    ) {
                        Text(text = "笔友")
                    }
                    Button(
                        onClick = {
                            navController.navigate("mailbox_screen")
                        }
                    ) {
                        Text(text = "胶囊")
                    }
                    Button(
                        onClick = {
                            navController.navigate("mailbox_screen")
                        }
                    ) {
                        Text(text = "我的")
                    }
                }

            }
        } ,
        floatingActionButton = {
            FloatingActionButton(onClick = { /* ... */ }) {
                /* FAB content */
            }
        }
    ) {
        // Screen content
    }
}

@Preview
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    MailBoxScreen(navController = navController)
}