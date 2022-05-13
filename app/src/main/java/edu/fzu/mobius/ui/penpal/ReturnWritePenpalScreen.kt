package edu.fzu.mobius.ui.penpal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.base.NoShadowButton
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.MailEditor
import edu.fzu.mobius.compose.mailbox.item.Envelope
import edu.fzu.mobius.compose.penpal.PenItem
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.ui.common.NoShadowBottomAppBar
import edu.fzu.mobius.ui.common.NoShadowTopAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.ui.common.nav.float.NavFloatButton
import edu.fzu.mobius.ui.mailbox.MailBoxViewModel
import edu.fzu.mobius.ui.write.WritePenPalScreen
import edu.fzu.mobius.ui.write.lineItem

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun ReturnWritePenpalScreen(
    navController: NavController,
    items:List<lineItem>,
    onEditItemChange: (lineItem) -> Unit,
    otherNickname: String
) {
    val returnWritePenPalViewModel: ReturnWritePenPalViewModel = viewModel()
    var floatingVisible = returnWritePenPalViewModel.floatingVisible
    Scaffold(
        topBar = {
            NoShadowTopAppBar (
                modifier = Modifier
                    .height(220.dp)//TopBar的高度   相关属性都可以用modifier改
                    .fillMaxWidth()
                    ){
                ConstraintLayout {
                    val (back,title_,top_,name_,time_,mail_) = createRefs()
                    NoShadowButton(
                        onClick = {
                            singleTaskNav(navController,"pen_pal_screen")
                        },
                        modifier = Modifier
                            .constrainAs(back) {
                                top.linkTo(parent.top, margin = 0.dp)
                                start.linkTo(parent.start, margin = 0.dp)
                                bottom.linkTo(parent.bottom, margin = 170.dp)
                            }
                    ) {
                        UnspecifiedIcon(
                            painter = painterResource(id = R.mipmap.back_left_icon),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                        )
                    }
                    Text(
                        text = "解除关系",
                        modifier = Modifier
                            .constrainAs(title_) {
                                top.linkTo(parent.top, margin = 0.dp)
                                start.linkTo(parent.start, margin = 300.dp)
                                end.linkTo(parent.end, margin = 0.dp)
                                bottom.linkTo(parent.bottom, margin = 170.dp)
                            },
                        textAlign = TextAlign.Right,
                        color = Color(0xFF17418B),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                    UnspecifiedIcon(
                        painter = painterResource(id = R.mipmap.head_girl),
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .constrainAs(top_) {
                                start.linkTo(parent.start, margin = 0.dp)
                                end.linkTo(parent.end, margin = 220.dp)
                                top.linkTo(parent.top, margin = 0.dp)
                                bottom.linkTo(parent.bottom, margin = 10.dp)
                            }
                    )
                    Text(
                        text = "hzd",
                        modifier = Modifier
                            .constrainAs(name_) {
                                top.linkTo(parent.top, margin = 0.dp)
                                start.linkTo(parent.start, margin = 0.dp)
                                end.linkTo(parent.end, margin = 0.dp)
                                bottom.linkTo(parent.bottom, margin = 50.dp)
                            },
                        textAlign = TextAlign.Left,
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = "成为笔友已有100天",
                        modifier = Modifier
                            .constrainAs(time_) {
                                top.linkTo(parent.top, margin = 20.dp)
                                start.linkTo(parent.start, margin = 150.dp)
                                end.linkTo(parent.end, margin = 0.dp)
                                bottom.linkTo(parent.bottom, margin = 0.dp)
                            },
                        textAlign = TextAlign.Left,
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = "|  交往信件",
                        modifier = Modifier
                            .constrainAs(mail_) {
                                top.linkTo(parent.top, margin = 180.dp)
                                start.linkTo(parent.start, margin = 0.dp)
                                end.linkTo(parent.end, margin = 240.dp)
                                bottom.linkTo(parent.bottom, margin = 0.dp)
                            },
                        textAlign = TextAlign.Left,
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
        },
        bottomBar = {

        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = floatingVisible.value,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight*2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight*2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ) {
                NavFloatButton {
                    navController.navigate("write_revert_pen_pal_screen")
                }
            }
        }
    ) {
        ConstraintLayout() {
            val (edit, card) = createRefs()
            LazyColumn {
                item {
                    PenItem(userNickname = "hzd", abstract = "我想在这里告诉你个秘密..", otherNickname = "皇埔铁牛", type = 1, modifier = Modifier.animateItemPlacement())
                }
                item {
                    PenItem(userNickname = "皇埔铁牛", abstract = "那么问题来了是什么秘密呢", otherNickname = "hzd", type = 1, modifier = Modifier.animateItemPlacement())
                }
                item {
                    PenItem(userNickname = "hzd", abstract = "预知后事如何请听下回分析", otherNickname = "皇埔铁牛", type = 1, modifier = Modifier.animateItemPlacement())
                }
            }
        }
    }
}
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewReturn(){
    val lists = listOf(lineItem(""), lineItem(""))

    ReturnWritePenpalScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = {(lineItem)->{}},
        otherNickname ="笔友一号"
    )
}