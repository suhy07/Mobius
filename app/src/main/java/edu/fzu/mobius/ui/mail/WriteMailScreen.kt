package edu.fzu.mobius.ui.mail


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import edu.fzu.mobius.ui.common.NoShadowBottomAppBar
import edu.fzu.mobius.ui.common.mailbox.top.MailBoxTop
import edu.fzu.mobius.ui.theme.BlueBackground
import edu.fzu.mobius.ui.theme.BlueButton
import edu.fzu.mobius.ui.theme.PrimaryVariant
import java.util.*

@Composable
fun WriteMailScreen(
    navController: NavController,
    items:List<lineItem>,
    onEditItemChange: (lineItem) -> Unit,
) {
    var count by remember { mutableStateOf(0) }
    Scaffold(
        topBar = { MailBoxTop(navController = navController, router = "mailbox_screen") },
        backgroundColor = BlueBackground,
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
            val listState = rememberLazyListState()

            LazyColumn(
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
            , state = listState
            ){
                items(items = items){
                    val focusRequester = remember {
                        FocusRequester()
                    }

                    val item = it
                    LineInput(
                        text = it.value,
                        onTextChange = { onEditItemChange(item.copy(it)) },
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewWriteMail(){
//    WriteMailScreen(navController = rememberNavController())
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LineInput(
    text:String,
    onTextChange: (String) -> Unit,
){
    TextField(
        value = text,
        onValueChange = onTextChange,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Unspecified,
            focusedIndicatorColor = PrimaryVariant,
            unfocusedIndicatorColor = Color(0xFFBDBDBD),
            cursorColor = Color.Blue
        ),
        textStyle = TextStyle(
            fontSize = 18.sp,
            textDecoration = TextDecoration.None,
            lineHeight = 20.sp,
            textAlign = TextAlign.Left
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
     )
}