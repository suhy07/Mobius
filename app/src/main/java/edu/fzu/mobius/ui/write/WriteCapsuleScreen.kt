package edu.fzu.mobius.ui.write

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.ButtonBottom
import edu.fzu.mobius.compose.LineInput
import edu.fzu.mobius.compose.MailEditor
import edu.fzu.mobius.compose.penpal.PenOtherUser
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.theme.bluetext
import edu.fzu.mobius.ui.common.NoShadowBottomAppBar
import edu.fzu.mobius.ui.penpal.PenPalViewModel
import edu.fzu.mobius.ui.penpal.Penpal
import java.util.*
import kotlin.reflect.KMutableProperty0


@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun WriteCapsuleScreen(
    navController: NavController,
    items:List<lineItem>,
    onEditItemChange: (lineItem) -> Unit,
    otherNickname: String,
    card:Boolean,
    sure:Boolean,
    return1:Boolean,
    sendWriteCapsule: (NavController)->Unit,
    writeCapsuleViewModel:WriteCapsuleViewModel,
    value: MutableState<String> = mutableStateOf("test"),
) {
    val mDate = remember { mutableStateOf("") }
    val isClick = rememberSaveable  { mutableStateOf(false) }
    var selectList: MutableList<String> = mutableListOf("我自己","好友1","好友2","好友3","好友4")
    val selectType = rememberSaveable { mutableStateOf(selectList[0])}
    var cardVisible by remember { mutableStateOf(false) }
    var sureVisible by remember { mutableStateOf(true) }
    var returnVisible by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    var Time by remember { mutableStateOf("") }

    Log.e("AAAAAA",Penpal.friendlist.size.toString())
    for (i in 0 until  Penpal.friendlist.size){
        writeCapsuleViewModel.selectList.add(Penpal.friendlist[i].nickname)
        writeCapsuleViewModel.selectIdList.add(Penpal.friendlist[i].id)
    }
    writeCapsuleViewModel.title.value = "给未来的信"
    cardVisible = card;
    sureVisible = sure;
    returnVisible = return1;
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mH:Int
    val mm:Int
    val ms:Int
    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            val mH:Int
            val mm:Int
            val ms:Int
            mH = mCalendar.get(Calendar.HOUR_OF_DAY)
            mm = mCalendar.get(Calendar.MINUTE)
            ms = mCalendar.get(Calendar.SECOND)
            mDate.value = "$mYear-${mMonth+1}-$mDayOfMonth $mH:$mm:$ms"
        }, mYear, mMonth, mDay
    )

    Scaffold(
        topBar = {
            BaseTitleTop(
                navController = navController,
                router = "capsule_screen"
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = sureVisible,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ) {
            NoShadowBottomAppBar(
                modifier = Modifier
                    .padding(12.dp)
                    .height(120.dp)


            ) {
                Column() {

                    TextButton(
                        onClick = { /*TODO*/
                            cardVisible = true
                            sureVisible = false
                        },
                        shape = RoundedCornerShape(20.dp),
                        elevation = ButtonDefaults.elevation(10.dp, 10.dp, 10.dp),
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = BlueButton,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "发送信件",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
            AnimatedVisibility(
                visible = cardVisible,
                modifier = Modifier

                    .background(Color.Unspecified),
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ) { // this: AnimatedVisibilityScope
                // Use AnimatedVisibilityScope#transition to add a custom animation
                // to the AnimatedVisibility.
                Card(
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .background(Color.Unspecified)
                ) {
                    ConstraintLayout() {
                        val (cardtext, slider,row, cardtext1, cardbutton) = createRefs()
                        val progress = remember { mutableStateOf(0f) }
                        Text(
                            text = "设置忧郁值"+"   "+(progress.value*100).toInt()+"%",
                            modifier = Modifier
                                .constrainAs(cardtext) {
                                    start.linkTo(parent.start, margin = 15.dp)
                                    top.linkTo(parent.top, margin = 25.dp)
                                },
                            fontSize = 16.sp,)
                        Slider(
                            value = progress.value,
                            onValueChange = {
                                progress.value = it
                            },
                            colors = SliderDefaults.colors(
                                thumbColor = Color.Blue,
                                inactiveTrackColor = Color.LightGray,
                                activeTrackColor = Color.Blue
                            ),
                            modifier = Modifier
                                .constrainAs(slider) {
                                    start.linkTo(parent.start, margin = 5.dp)
                                    top.linkTo(parent.top, margin = 55.dp)
                                },
                        )
                        Row(
                            modifier = Modifier
                                .constrainAs(row) {
                                    start.linkTo(parent.start, margin = 15.dp)
                                    top.linkTo(parent.top, margin = 105.dp) },
                        )
                        {
                            Text(
                                modifier = Modifier
                                    .padding(start = 0.dp,top = 10.dp)
                                ,
                                text = "设置收信时间  ",
                                fontSize = 14.sp,
                            )
                            Button(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(35.dp)
                                ,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.White,
                                ),
                                onClick = {
                                    mDatePickerDialog.show()}
                            ){
                                Icon(painter = painterResource(id = R.drawable.calendar), null)
                                Text(text = mDate.value)
                            }
                        }
                        Text(
                            text = "每次邀请使用一张邮票",
                            modifier = Modifier
                                .constrainAs(cardtext1) {
                                    start.linkTo(parent.start, margin = 15.dp)
                                    top.linkTo(parent.top, margin = 165.dp)
                                },
                            fontSize = 14.sp,
                            color = bluetext,)
                        TextButton(
                            onClick = { /*TODO 发送成功*/
                                writeCapsuleViewModel.content.value = value.value
                                writeCapsuleViewModel.arriveTime.value = mDate.value
                                Log.e("AAAAAA",writeCapsuleViewModel.arriveTime.value)
                                Log.e("AAAAAA",writeCapsuleViewModel.content.value)
                                Log.e("AAAAAA",writeCapsuleViewModel.receiverId.value.toString())
                                Log.e("AAAAAA",writeCapsuleViewModel.title.value)
                                sendWriteCapsule(navController)
//                                navController.navigate("capsule_success_screen")
                            },
                            shape = RoundedCornerShape(20.dp),
                            elevation = ButtonDefaults.elevation(10.dp, 10.dp, 10.dp),
                            colors = ButtonDefaults.textButtonColors(
                                backgroundColor = BlueButton,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .height(60.dp)
                                .width(365.dp)
                                .constrainAs(cardbutton) {
                                    start.linkTo(parent.start, margin = 25.dp)
                                    top.linkTo(parent.top, margin = 205.dp)
                                },
                        ) {
                            Text(
                                text = "确定",
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
            AnimatedVisibility(
                visible = returnVisible,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight * 2 },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
                ),
            ){
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
                                    modifier = Modifier.animateItemPlacement(),
                                    navController
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
        }
    ) {
        ConstraintLayout() {
            val (edit, card,card1) = createRefs()
//            MailEditor(
//                otherNickname = otherNickname,
//                items = items,
//                onEditItemChange = onEditItemChange,
//                modifier = Modifier
//                    .constrainAs(edit) {
//                    },
//            )
            Box(
                modifier = Modifier
                    .constrainAs(edit) {
                    },
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    if (otherNickname.equals("")){
                        item() {
                            Row {

                                Text(
                                    text = "To:",
                                    modifier = Modifier
                                        .width(60.dp)
                                        .padding(start = 0.dp, top = 10.dp)
                                    ,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                )
                                Button(
                                    onClick = {isClick.value = !isClick.value},
                                    content = {
                                        Text(text = selectType.value)
                                    },
                                    modifier = Modifier
                                        .width(120.dp),
                                    shape = RoundedCornerShape(5.dp),
                                    border = BorderStroke(1.dp,Color.Blue) ,
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.White,
                                    )
                                )
                                DropdownMenu(
                                    expanded = isClick.value,
                                    modifier = Modifier.fillMaxWidth(),
                                    onDismissRequest = {},
                                    content = {
                                        writeCapsuleViewModel.selectList.forEach {
                                            DropdownMenuItem(
                                                onClick = {
                                                    isClick.value = !isClick.value
                                                    selectType.value = it
                                                },
                                                content = {
                                                    Text(text = it)
                                                    for (i in 0 until  Penpal.friendlist.size){
                                                        if (it.equals(writeCapsuleViewModel.selectList[i]))
                                                        writeCapsuleViewModel.receiverId.value=writeCapsuleViewModel.selectIdList[i]
                                                    }
                                                }
                                            )
                                        }
                                    }
                                )

                            }
                        }
                    }
                    else {
                        item() {
                            LineInput(
                                text = "To:$otherNickname",
                                onTextChange = {},
                                readOnly = true,
                                modifier = Modifier
                                    .width(120.dp),
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                    items(items = items){
                        val item = it
                        LineInput(
                            text = it.value,
                            onTextChange = { onEditItemChange(item.copy(value = it)) },
                        )
                    }
                }
            }
            Log.d("ASD", isClick.value.toString())
//            DropdownMenu(
//                expanded = isClick.value,
//                modifier = Modifier.fillMaxWidth(),
//                onDismissRequest = {},
//                content = {
//                    selectList.forEach {
//                        DropdownMenuItem(
//                            onClick = {
//                                isClick.value = !isClick.value
//                                selectType.value = it
//                            },
//                            content = {
//                                Text(text = it)
//                            }
//                        )
//                    }
//                }
//            )

        }
    }
}


@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
fun CapsulePreviewWriteMail(){
    val lists = listOf(lineItem(""), lineItem(""))
    val writeCapsuleViewModel: WriteCapsuleViewModel = viewModel()

    WriteCapsuleScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = { (lineItem)->{}},
        otherNickname ="",
        card = true,
        sure = false,
        return1 = false,
        sendWriteCapsule = writeCapsuleViewModel::sendWriteCapsule,
        writeCapsuleViewModel = writeCapsuleViewModel
    )
}