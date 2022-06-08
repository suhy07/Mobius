package edu.fzu.mobius.ui.signin

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.fzu.mobius.compose.CloseButton
import edu.fzu.mobius.compose.SignInBigItemScreen
import edu.fzu.mobius.compose.SignInItemScreen
import edu.fzu.mobius.entity.SignInItem
import edu.fzu.mobius.ui.mailbox.MailBoxViewModel
import edu.fzu.mobius.util.TimeUtil

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignInPop(modifier: Modifier = Modifier) {
    val mailBoxViewModel: MailBoxViewModel = viewModel()
    val signInViewModel: SignInViewModel = viewModel()
    var items: List<SignInItem> = signInViewModel.items
    var signInVisible = mailBoxViewModel.signInVisible
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .height(440.dp)
            .width(300.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                ) {
                    val (title, close) = createRefs()
                    Text(
                        text = "签到",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .constrainAs(title) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }
                    )
                    CloseButton(
                        onClick = {
                            signInVisible.value = false
                        },
                        modifier = Modifier
                            .constrainAs(close) {
                                end.linkTo(parent.end, margin = 30.dp)
                            }
                            .size(15.dp)
                    )
                }
            }
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 80.dp),
                modifier = Modifier
                    .height(400.dp)
                    .width(300.dp)
            ) {
                items(items) {
                    when {
                        it.day != 7 -> {
                            if (TimeUtil.getTodayWeek() == it.day) {
                                Log.d("TAGTAG", TimeUtil.getTodayWeek().toString()+":"+it.day)
                                SignInItemScreen(gift = it.gift, state = it.state, true)
                            }
                            else
                                SignInItemScreen(gift = it.gift, state = it.state )
                        }
                        it.day == 7 -> Spacer(modifier = Modifier.size(80.dp))
                    }
                }
                item {
                    val item = signInViewModel.getBigItem()
                    if (TimeUtil.getTodayWeek() == item.day)
                        SignInBigItemScreen(gift = item.gift, state = item.state, true)
                    else
                        SignInBigItemScreen(gift = item.gift, state = item.state)
                }
                item {
                    Spacer(modifier = Modifier.size(80.dp))
                }
            }
        }
    }
}
@Preview
@Composable
fun PreviewSignInPop() {
    SignInPop()
}