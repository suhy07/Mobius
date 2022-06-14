package edu.fzu.mobius.ui.mail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.theme.bluetext
import edu.fzu.mobius.ui.common.UnspecifiedIcon

@Composable
fun ReadMailScreen(
    id: Int ,
    navController: NavController
) {
    val readMailViewModel: ReadMailViewModel = viewModel()
    val cardVisible = readMailViewModel.cardVisible
    val otherNickname = readMailViewModel.otherNickname
    val writeMailViewModel: WriteMailViewModel = viewModel()
    val items = writeMailViewModel.LineItems
    val onEditItemChange = writeMailViewModel::onEditItemChange
    Scaffold(
        topBar = {
            ConstraintLayout {
                val (report) = createRefs()
                UnspecifiedIcon(
                    painter = painterResource(id = R.mipmap.report_icon),
                    modifier = Modifier
                        .height(30.dp)
                        .constrainAs(report) {
                            end.linkTo(parent.end, margin = 20.dp)
                            top.linkTo(parent.top, margin = 0.dp)
                        }
                )
            }
            BaseTitleTop(
                navController = navController,
                router = "anon_mailbox_screen"
            ) },
        bottomBar = {
            ButtonBottom(
                onClick = {
                    cardVisible.value = true
                },
                title = "发送信件"
            )
        }
    ) {
        ConstraintLayout {
            val (report,edit) = createRefs()
            MailEditor(
                otherNickname = otherNickname.value,
                items = items,
                onEditItemChange = onEditItemChange,
                modifier = Modifier
                    .constrainAs(edit){

                    },
                isPanPal = false
            )
        }
    }
}

@Preview
@Composable
fun PreviewRead(){
    val navController = rememberNavController()
    ReadMailScreen(
        id = 0,
        navController = navController
    )
}