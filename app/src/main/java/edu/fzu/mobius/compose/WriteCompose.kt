package edu.fzu.mobius.compose

import android.content.res.Resources
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import edu.fzu.mobius.R
import edu.fzu.mobius.theme.PrimaryVariant
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.ui.mail.LineItem
import edu.fzu.mobius.util.BitmapUtil

@Composable
fun MailEditor(
    otherNickname:String,
    items: List<LineItem>,
    onEditItemChange: (LineItem) -> Unit,
    modifier: Modifier = Modifier,
    isPanPal: Boolean = true,
    readOnly: Boolean = false
){
    var selectList: MutableList<String> = mutableListOf("我自己","好友1","好友2","好友3","好友4")
    val isClick = remember{ mutableStateOf(false)}
    val selectType = remember{ mutableStateOf(selectList[0])}
    Box(
        modifier = modifier
            .padding(start = 25.dp , end = 25.dp , bottom = 100.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ){
            if (otherNickname.equals("") && isPanPal){
                item {
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
                            selectList.forEach {
                                DropdownMenuItem(
                                    onClick = {
                                        isClick.value = !isClick.value
                                        selectType.value = it
                                    },
                                    content = {
                                        Text(text = it)
                                    }
                                )
                            }
                        }
                    )

                    }
                }
            }
            else {
                item {
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
                    readOnly = readOnly,
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
fun PreviewMailEditor(){
    val lists = listOf(LineItem(""), LineItem(""))
    MailEditor(
        items = lists,
        onEditItemChange = {(LineItem)->{}},
        otherNickname = "陌生人",
    )
}