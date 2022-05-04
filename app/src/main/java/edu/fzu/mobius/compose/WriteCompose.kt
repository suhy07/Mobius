package edu.fzu.mobius.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.fzu.mobius.theme.PrimaryVariant
import edu.fzu.mobius.ui.write.lineItem

@Composable
fun MailEditor(
    otherNickname:String,
    items: List<lineItem>,
    onEditItemChange: (lineItem) -> Unit,
    modifier: Modifier = Modifier,
    enable: Boolean,
    onClick:()->Unit ={},
){
//    val isClick = rememberSaveable{ mutableStateOf(false)}
//    val selectType = rememberSaveable{ mutableStateOf(selectList[0])}
    Box(
        modifier = modifier
            .padding(start = 25.dp , end = 25.dp , bottom = 100.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ){
//            if (otherNickname.equals("")){
//
//                item() {
//                    Row {
//
//                    Text(
//                        text = "To:",
//                        modifier = Modifier
//                            .width(60.dp)
//                            .padding(start = 0.dp,top = 10.dp)
//                        ,
//                        fontSize = 16.sp,
//                        textAlign = TextAlign.Center,
//                    )
//                    Button(
//                        onClick = {isClick.value = !isClick.value},
//                        content = {
//                            Text(text = selectType.value)
//                        },
//                        modifier = Modifier
//                            .width(120.dp),
//                        shape = RoundedCornerShape(5.dp),
//                        border = BorderStroke(1.dp,Color.Blue) ,
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = Color.White,
//                        )
//                    )
//                    DropdownMenu(
//                        expanded = isClick.value,
//                        modifier = Modifier.fillMaxWidth(),
//                        onDismissRequest = {},
//                        content = {
//                            selectList.forEach {
//                                DropdownMenuItem(
//                                    onClick = {
//                                        isClick.value = !isClick.value
//                                        selectType.value = it
//                                    },
//                                    content = {
//                                        Text(text = it)
//                                    }
//                                )
//                            }
//                        }
//                    )
//
//                    }
//                }
//            }
//            else {
                item() {
                    LineInput(
                        text = "To:$otherNickname",
                        onTextChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .width(120.dp)
                            .clickable(
                                enabled = enable,
                                onClick = onClick
                            ),
                        fontWeight = FontWeight.Bold
                    )
                }
//            }
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
    fontWeight: FontWeight = FontWeight.Normal,
    enable: Boolean = true,
    onClick:()->Unit ={}
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
            .clickable(
                enabled = enable,
                onClick = onClick
            )
    )
}

@Preview
@Composable
fun PreviewMailEditor(){
    val lists = listOf(lineItem(""), lineItem(""))
    MailEditor(
        items = lists,
        onEditItemChange = {(lineItem)->{}},
        otherNickname = "菜鸟",
        enable = false
    )
}