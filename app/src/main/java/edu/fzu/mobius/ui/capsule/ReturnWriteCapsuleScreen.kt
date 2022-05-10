package edu.fzu.mobius.ui.capsule

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.MailEditor
import edu.fzu.mobius.compose.penpal.PenOtherUser
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.ui.common.NoShadowBottomAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.ui.write.WritePenPalScreen
import edu.fzu.mobius.ui.write.lineItem

@ExperimentalFoundationApi
@Composable
fun ReturnWriteCapsuleScreen(
    navController: NavController,
    items:List<lineItem>,
    onEditItemChange: (lineItem) -> Unit,
    otherNickname: String
) {
    Scaffold(
        topBar = {
            BaseTitleTop(
                navController = navController,
                router = "capsule_screen"
            ) },
        bottomBar = {
            NoShadowBottomAppBar(
                modifier = Modifier
                    .padding(12.dp)
                    .height(120.dp)


            ) {
                Column() {
                    LazyColumn(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .padding(start = 200.dp, top = 0.dp)
                    ) {
                        items(1) {
                            PenOtherUser(
                                nickname = "黄埔铁牛",
                                modifier = Modifier.animateItemPlacement()
                            )
                        }
                    }

                    Text(
                        text = "信件将于4月1日到达",
                        textAlign = TextAlign.Center,
                        color = Color.Blue,
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                            .padding(start = 0.dp, top = 20.dp),
                        fontSize = 16.sp,
                    )
                }
            }

        }
    ) {
        ConstraintLayout() {
            val (edit, card) = createRefs()
            MailEditor(
                otherNickname = otherNickname,
                items = items,
                onEditItemChange = onEditItemChange,
                modifier = Modifier
                    .constrainAs(edit) {
                    },
            )

        }
    }
}
@ExperimentalFoundationApi
@Preview
@Composable
fun CapsulePreviewReturn(){
    val lists = listOf(lineItem(""), lineItem(""))

    ReturnWriteCapsuleScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = {(lineItem)->{}},
        otherNickname ="好友1"
    )
}