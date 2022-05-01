package edu.fzu.mobius.ui.write


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.ui.common.NoShadowBottomAppBar
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.compose.mailbox.top.MailBoxTop
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.theme.PrimaryVariant

@Composable
fun WriteMailScreen(
    navController: NavController,
    items:List<lineItem>,
    onEditItemChange: (lineItem) -> Unit,
    otherNickname: String
) {
    Scaffold(
        topBar = {
            MailBoxTop(
                navController = navController,
                router = "mailbox_screen"
            ) },
        bottomBar = {
            NoShadowBottomAppBar(
                modifier = Modifier
                    .padding(12.dp)
                    .height(100.dp)
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.elevation(10.dp, 10.dp, 10.dp),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = BlueButton,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "发送信件",
                        fontSize = 20.sp
                    )
                }
            }
        }
    ) {
        ConstraintLayout() {
            val (icon,edit) = createRefs()
            UnspecifiedIcon(
                painter = painterResource(id = R.drawable.letter1),
                modifier = Modifier
                    .height(60.dp)
                    .constrainAs(icon){
                        end.linkTo(parent.end, margin = 20.dp)
                        top.linkTo(edit.top, margin = 0.dp)
                    }
            )
            MailEditor(
                otherNickname = otherNickname,
                items = items,
                onEditItemChange = onEditItemChange,
                modifier = Modifier
                    .constrainAs(edit){

                    }
            )
        }
    }
}

@Composable
fun MailEditor(
    otherNickname:String,
    items: List<lineItem>,
    onEditItemChange: (lineItem) -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .padding(start = 25.dp , end = 25.dp , bottom = 100.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ){
            item(){
                LineInput(
                    text = "To:$otherNickname",
                    onTextChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .width(120.dp),
                    fontWeight = FontWeight.Bold
                )
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
}

@Composable
fun LineInput(
    text:String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    fontWeight: FontWeight = FontWeight.Normal
){
    TextField(
        value = text,
        onValueChange = onTextChange,
        readOnly = readOnly,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Unspecified,
            focusedIndicatorColor = PrimaryVariant,
            unfocusedIndicatorColor = Color(0xFFBDBDBD),
            cursorColor = Color.Blue
        ),
        textStyle = TextStyle(
            fontSize = 16.sp,
            textDecoration = TextDecoration.None,
            lineHeight = 30.sp,
            textAlign = TextAlign.Left,
            fontWeight = fontWeight
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp)
            .height(50.dp)
     )
}

@Preview
@Composable
fun PreviewWriteMail(){
    val lists = listOf(lineItem(""), lineItem(""))
    WriteMailScreen(
        navController = rememberNavController(),
        items = lists,
        onEditItemChange = {(lineItem)-> run {} },
        otherNickname ="陌生人"
    )
}


@Preview
@Composable
fun PreviewMailEditor(){
    val lists = listOf(lineItem(""), lineItem(""))
    MailEditor(
        items = lists,
        onEditItemChange = {(lineItem)->{}},
        otherNickname = "陌生人"
    )
}