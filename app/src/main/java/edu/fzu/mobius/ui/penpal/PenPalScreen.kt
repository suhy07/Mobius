package edu.fzu.mobius.ui.penpal

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
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
import edu.fzu.mobius.base.NoShadowButton
import edu.fzu.mobius.compose.mailbox.item.OtherUser
import edu.fzu.mobius.compose.penpal.AddPenOtherUser
import edu.fzu.mobius.compose.penpal.PenOtherUser
import edu.fzu.mobius.compose.penpal.PenOtherUser1
import edu.fzu.mobius.entity.StrangeDataProject
import edu.fzu.mobius.entity.TestData
import edu.fzu.mobius.ui.common.NoShadowTopAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.ui.common.nav.bottom.NavBottom
import edu.fzu.mobius.theme.BlueBackground
import edu.fzu.mobius.theme.PrimaryVariant
import edu.fzu.mobius.ui.login.LoginViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun PenPalScreen(
    navController: NavController,
    friendlist: MutableState<List<TestData.Data.Project>>,
    strangelist: MutableState<List<StrangeDataProject>>,
    strangeNickName: MutableState<String>,
    penPalList: ()->Unit,
    addPenPalList: ()->Unit,
//    penPalViewModel: PenPalViewModel
){
//    Log.e("AAAAAAAAAAAAA",friendlist.toString())
//    val penPalViewModel: PenPalViewModel = viewModel()
    var cardVisible by remember { mutableStateOf(false) }
    var popVisible by remember { mutableStateOf(false) }
//    var text by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            NoShadowTopAppBar (
                modifier = Modifier
                    .height(55.dp)//TopBar?????????   ????????????????????????modifier???
                    ){
                ConstraintLayout {
                    NoShadowButton(
                        onClick = { /* TODO ????????????????????? */
                            cardVisible = !cardVisible
                            popVisible = false
                            penPalList()
                            addPenPalList()
//                            Log.e("ACDD",friendlist[0].nickname)
                                  },

                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .padding(start = 5.dp,top = 5.dp)
                    ){
                        UnspecifiedIcon(
                            painter = painterResource(R.mipmap.anony_icon)
                        )
                    }
                }

            }},
        backgroundColor = BlueBackground,
        bottomBar = {
            NavBottom(navController = navController, act = 1)
        } ,
    ) {
        // Screen content
        ConstraintLayout {
                // Create references for the composables to constrain
                val (image,text1,text2,text3,image2,card1,card2) = createRefs()

                Image(
                    modifier = Modifier
                        .fillMaxSize()
//                        .fillMaxSize() ??????????????????????????????????????????parent MaxHeight&&MaxWidth
//                        .fillMaxHeight() MaxHeight
//                        .fillMaxWidth() MaxWidth
                        .constrainAs(image) {
                            bottom.linkTo(parent.bottom, margin = 0.dp)
                        }
                        .height(820.dp)
                        .width(820.dp),
                    painter = painterResource(id = R.drawable.pigeon),
                    contentDescription = "background_image",
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = "????????????????????????...",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(text1) {
                            start.linkTo(parent.start, margin = 80.dp)
                            bottom.linkTo(parent.bottom, margin = 620.dp)
                        },
                )
                Text(
                    text = "?????????????????????.",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(text2) {
                            start.linkTo(parent.start, margin = 80.dp)
                            bottom.linkTo(parent.bottom, margin = 590.dp)
                        }
                )
                Text(
                    text = "????????????????????????",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                       .constrainAs(text3) {
                            start.linkTo(parent.start, margin = 80.dp)
                            bottom.linkTo(parent.bottom, margin = 560.dp)
                        }
                )
                Image(
                    modifier = Modifier
//                            .fillMaxSize() ??????????????????????????????????????????parent MaxHeight&&MaxWidth

                        .constrainAs(image2) {
                            start.linkTo(parent.start, margin = 260.dp)
                            bottom.linkTo(parent.bottom, margin = 540.dp)
                        }
                        .height(190.dp)
                        .width(120.dp),
                    painter = painterResource(id = R.drawable.letter2),
                    contentDescription = "letter_image",
                    contentScale = ContentScale.FillWidth
                )
            AnimatedVisibility(
                visible = cardVisible,
                modifier= Modifier
                    .constrainAs(card1) {
                        start.linkTo(parent.start, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 40.dp)
                    }
                    .background(Color.Unspecified),
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight*2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight*2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ) { // this: AnimatedVisibilityScope
                // Use AnimatedVisibilityScope#transition to add a custom animation
                // to the AnimatedVisibility.
                Card(
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .height(550.dp)
                        .background(Color.Unspecified)
                ) {
                    ConstraintLayout() {
                        val (cardtext1, close, row, list,add) = createRefs()
                        var text by remember { mutableStateOf("") }
                        Text(
                            text = "????????????",
                            modifier = Modifier
                                .constrainAs(cardtext1) {
                                    start.linkTo(parent.start, margin = 140.dp)
                                    top.linkTo(parent.top, margin = 25.dp)
                                },
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        NoShadowButton(
                            onClick = {
                                cardVisible = false
                            },
                            modifier = Modifier
                                .constrainAs(close) {
                                    end.linkTo(parent.end, margin = 10.dp)
                                    top.linkTo(parent.top, margin = 10.dp)
                                }
                        ) {
                            UnspecifiedIcon(
                                painter = painterResource(id = R.mipmap.close_icon),
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                            )
                        }

                        TextField(
                            value = text,
                            onValueChange = {
                                text = it
                            },
                            modifier = Modifier
                                .height(55.dp)
                                .constrainAs(row){
                                    start.linkTo(parent.start, margin = 48.dp)
                                    top.linkTo(parent.top, margin = 65.dp)
                                }
                                .border(1.dp,Color(0xFFE5E5E5),shape = RoundedCornerShape(20.dp))
                            ,

                            singleLine = true,
                            leadingIcon = @Composable {
                                Image(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "search",
                                    modifier = Modifier.clickable {

                                    }
                                )
                            },
                            trailingIcon = @Composable {
                                Image(imageVector = Icons.Filled.Clear,
                                    contentDescription = "clear",
                                    modifier = Modifier.clickable {
                                        text = ""
                                    })
                            },
                            placeholder = @Composable{
                                Text(text = "???????????????")
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor =  Color.Unspecified,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor =  Color.White,
                                cursorColor = Color.Blue
                            ),
                            shape= RoundedCornerShape(20f)
                        )
                        LazyColumn(
                            modifier = Modifier
                                .constrainAs(list) {
                                    start.linkTo(parent.start, margin = 40.dp)
                                    top.linkTo(parent.top, margin = 130.dp)
                                }
                        ) {

                            items(friendlist.value.size) {
                                PenOtherUser(
                                    nickname = friendlist.value[it].nickname,
                                    id = friendlist.value[it].id,
                                    modifier = Modifier.width(200.dp).padding(top = 10.dp),
                                    navController
                                )
                            }
                        }
                                NoShadowButton(
                                    onClick = { /* TODO ??????????????????????????? */
                                        cardVisible = false;
                                        popVisible = true;
                                              },

                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(50.dp)
                                        .padding(start = 0.dp,top = 0.dp)
                                        .constrainAs(add) {
                                            end.linkTo(parent.end, margin = 30.dp)
                                            bottom.linkTo(parent.bottom, margin = 50.dp)
                                        }
                                ){
                                    UnspecifiedIcon(
                                        painter = painterResource(R.mipmap.add_icon)
                                    )
                                }
                            }

//                    }
                }
                }

            AnimatedVisibility(
                visible = popVisible,
                modifier= Modifier
                    .constrainAs(card2) {
                        start.linkTo(parent.start, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 180.dp)
                    }
                    .background(Color.Unspecified),
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight*2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight*2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ) { // this: AnimatedVisibilityScope
                // Use AnimatedVisibilityScope#transition to add a custom animation
                // to the AnimatedVisibility.
                Card(
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp,bottomStart = 20.dp, bottomEnd = 20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .height(450.dp)
                        .background(Color.Unspecified)
                ){
                    ConstraintLayout() {
                        val (cardtext2, close2, row2, list2,add2) = createRefs()
                        var text by remember { mutableStateOf("") }
                        Text(
                            text = "??????????????????",
                            modifier = Modifier
                                .constrainAs(cardtext2) {
                                    start.linkTo(parent.start, margin = 140.dp)
                                    top.linkTo(parent.top, margin = 25.dp)
                                },
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        NoShadowButton(
                            onClick = {
                                popVisible = false
                            },
                            modifier = Modifier
                                .constrainAs(close2) {
                                    end.linkTo(parent.end, margin = 10.dp)
                                    top.linkTo(parent.top, margin = 10.dp)
                                }
                        ) {
                            UnspecifiedIcon(
                                painter = painterResource(id = R.mipmap.close_icon),
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                            )
                        }
                        TextField(
                            value = text,
                            onValueChange = {
                                text = it
                            },
                            modifier = Modifier
                                .height(55.dp)
                                .constrainAs(row2){
                                    start.linkTo(parent.start, margin = 48.dp)
                                    top.linkTo(parent.top, margin = 65.dp)
                                }
                                .border(1.dp,Color(0xFFE5E5E5),shape = RoundedCornerShape(20.dp))
                            ,

                            singleLine = true,
                            leadingIcon = @Composable {
                                Image(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "search",
                                    modifier = Modifier.clickable {
                                        strangeNickName.value=text
                                        addPenPalList()
                                    }
                                )
                            },
                            trailingIcon = @Composable {
                                Image(imageVector = Icons.Filled.Clear,
                                    contentDescription = "clear",
                                    modifier = Modifier.clickable {
                                        text = ""
                                    })
                            },
                            placeholder = @Composable{
                                Text(text = "???????????????")
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor =  Color.Unspecified,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor =  Color.White,
                                cursorColor = Color.Blue
                            ),
//                            colors = TextFieldDefaults.textFieldColors(backgroundColor =  Color.White),
                            shape= RoundedCornerShape(20f)
                            )
                        LazyColumn(
                            modifier = Modifier
                                .constrainAs(list2) {
                                    start.linkTo(parent.start, margin = 40.dp)
                                    top.linkTo(parent.top, margin = 140.dp)
                                }
                        ) {
                            items(strangelist.value){
                                AddPenOtherUser(
                                    nickname = it.nickname,
                                    id = it.id,
                                    modifier = Modifier.animateItemPlacement(),
                                    navController
                                )
                            }
                        }
//                        NoShadowButton(
//                            onClick = { /* TODO ???????????????????????? */
//                                navController.navigate("write_pen_pal_screen")
//                            },
//
//                            modifier = Modifier
//                                .height(100.dp)
//                                .width(100.dp)
//                                .padding(start = 0.dp,top = 0.dp)
//                                .constrainAs(add2) {
//                                    end.linkTo(parent.end, margin = 10.dp)
//                                    bottom.linkTo(parent.bottom, margin = 20.dp)
//                                }
//                        ){
//                            UnspecifiedIcon(
//                                painter = painterResource(R.mipmap.add_icon)
//                            )
//                        }
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewPenPal() {
    val navController = rememberNavController()
    val penPalViewModel: PenPalViewModel = viewModel()


    PenPalScreen(
        navController = navController,
        friendlist = penPalViewModel.friendlist,
        strangelist = penPalViewModel.strangelist,
        strangeNickName = penPalViewModel.strangenickname,
        penPalList = penPalViewModel::PenPalList,
        addPenPalList = penPalViewModel::AddPenPalList
    )
}