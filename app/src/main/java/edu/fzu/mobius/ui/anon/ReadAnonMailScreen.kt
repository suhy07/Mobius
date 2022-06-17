package edu.fzu.mobius.ui.mail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.MailEditor
import edu.fzu.mobius.global.GlobalMem
import edu.fzu.mobius.ui.anon.ReadAnonMailViewModel

import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.ui.common.nav.float.NavFloatButton

@Composable
fun ReadAnonMailScreen(
    id: Int ,
    navController: NavController
) {
    val readAnonMailViewModel: ReadAnonMailViewModel = viewModel()
    val flowVisible = readAnonMailViewModel.flowVisible
    val otherNickname = readAnonMailViewModel.otherNickname
    val writeMailViewModel: WriteMailViewModel = viewModel()
    val items = writeMailViewModel.LineItems
    val onEditItemChange = writeMailViewModel::onEditItemChange
    val imagePainter = painterResource(id = R.mipmap.head_girl)
    val lnkMargin = readAnonMailViewModel.lnkMargin
    readAnonMailViewModel.getInfo(id, writeMailViewModel::setLetterValue)
    Scaffold(
        topBar = {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (report) = createRefs()
                UnspecifiedIcon(
                    painter = painterResource(id = R.mipmap.report_icon),
                    modifier = Modifier
                        .height(30.dp)
                        .constrainAs(report) {
                            end.linkTo(parent.end, margin = 20.dp)
                            top.linkTo(parent.top, margin = 20.dp)
                        }
                )
            }
            BaseTitleTop(
                navController = navController,
                router = "anon_mailbox_screen"
            )
        },
        bottomBar = {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (nickname, icon, flower, reply) = createRefs()
                Text(
                    text = otherNickname.value,
                    modifier = Modifier
                        .constrainAs(nickname){
                            top.linkTo(parent.top, margin = 20.dp)
                            end.linkTo(parent.end, margin = 30.dp)
                        }
                )
                UnspecifiedIcon(
                    painter = imagePainter,
                    modifier = Modifier
                        .size(40.dp)
                        .constrainAs(icon) {
                            top.linkTo(parent.top, margin = 20.dp)
                            end.linkTo(nickname.start, margin = 10.dp)
                        }
                )
                AnimatedVisibility(
                    visible = flowVisible.value,
                    exit = fadeOut(),
                    modifier = Modifier
                        .constrainAs(flower) {
                            start.linkTo(parent.start, margin = 0.dp)
                            end.linkTo(parent.end, margin = 180.dp)
                            top.linkTo(parent.top, margin = 50.dp)
                        }
                ) {
                    NavFloatButton(
                        resource = R.mipmap.flow_icon,
                        onClick = {
                            readAnonMailViewModel.flower(id)
                            lnkMargin.value = 0.dp
                        }
                    )
                }
                NavFloatButton(
                    resource = R.drawable.lnk,
                    onClick = {
                        readAnonMailViewModel.reply(navController)
                    },
                    modifier = Modifier
                        .constrainAs(reply) {
                            start.linkTo(parent.start, margin = lnkMargin.value)
                            end.linkTo(parent.end, margin = 0.dp)
                            top.linkTo(parent.top, margin = 50.dp)
                        }
                )
            }
        }
    ) {
        MailEditor(
            otherNickname = GlobalMem.NICK_NAME,
            items = items,
            onEditItemChange = onEditItemChange,
            isPanPal = false,
            modifier = Modifier.padding(bottom = 60.dp),
            readOnly = true
        )
    }
}

@Preview
@Composable
fun PreviewRead(){
    val navController = rememberNavController()
    ReadAnonMailScreen(
        id = 0,
        navController = navController
    )
}