package edu.fzu.mobius.ui.penpal

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.ButtonBottom
import edu.fzu.mobius.compose.MailEditor
import edu.fzu.mobius.global.GlobalMem
import edu.fzu.mobius.ui.anon.WriteAnonViewModel
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.ui.common.nav.float.NavFloatButton
import edu.fzu.mobius.ui.mail.WriteMailViewModel

@Composable
fun InviteScreen(
    navController: NavController,
    id: Int,
    nickname: String
) {
    val inviteViewModel: InviteViewModel = viewModel()
    inviteViewModel.nickname.value = nickname
    val writeMailViewModel: WriteMailViewModel = viewModel()
    writeMailViewModel.setLetterValue(nickname+"想要和你成为笔友")
    Scaffold(
        topBar = {
            BaseTitleTop(
                navController = navController,
                router = "mailbox_screen"
            ) },
        bottomBar = {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (nickname, icon, botton) = createRefs()
                Text(
                    text = inviteViewModel.nickname.value,
                    modifier = Modifier
                        .constrainAs(nickname){
                            top.linkTo(parent.top, margin = 20.dp)
                            bottom.linkTo(parent.bottom, margin = 50.dp)
                            end.linkTo(parent.end, margin = 30.dp)
                        }
                )
                UnspecifiedIcon(
                    painter = painterResource(id = R.mipmap.head_girl),
                    modifier = Modifier
                        .size(40.dp)
                        .constrainAs(icon) {
                            top.linkTo(parent.top, margin = 20.dp)
                            bottom.linkTo(parent.bottom, margin = 50.dp)
                            end.linkTo(nickname.start, margin = 10.dp)
                        }
                )
                ButtonBottom(
                    onClick = {
                        inviteViewModel.applyInvite(navController, id)
                    },
                    title = "接受邀请",
                    modifier = Modifier.constrainAs(botton){
                        top.linkTo(nickname.bottom)
                    }
                )
            }
        }
    ) {
        ConstraintLayout {
            val (icon,edit) = createRefs()
            UnspecifiedIcon(
                painter = painterResource(id = R.drawable.letter1),
                modifier = Modifier
                    .height(60.dp)
                    .constrainAs(icon) {
                        end.linkTo(parent.end, margin = 20.dp)
                        top.linkTo(parent.top, margin = 0.dp)
                    }
            )
            MailEditor(
                otherNickname = GlobalMem.NICK_NAME,
                items = writeMailViewModel.LineItems,
                onEditItemChange = writeMailViewModel::onEditItemChange,
                modifier = Modifier
                    .constrainAs(edit){

                    },
                readOnly = true
            )
        }
    }
}

@Preview
@Composable
fun PreviewPenInvite(){
    InviteScreen(navController = rememberNavController(), id = 0, nickname = "")
}