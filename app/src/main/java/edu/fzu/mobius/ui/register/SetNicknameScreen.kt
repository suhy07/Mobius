package edu.fzu.mobius.ui.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.compose.EmptyTextField
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.ui.common.UnspecifiedIcon

@Composable
fun SetNicknameScreen(
    navController: NavController,
    nickname: MutableState<String>,
    setNickname: (NavController)->Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (head, edit, dog) = createRefs()
        UnspecifiedIcon(
            painter = painterResource(id = R.mipmap.head_girl),
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .constrainAs(head) {
                    top.linkTo(parent.top, margin = 0.dp)
                    start.linkTo(parent.start, margin = 0.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin = 400.dp)
                }
        )
        UnspecifiedIcon(
            painter = painterResource(id = R.drawable.setnickname_dog),
            modifier = Modifier
                .height(300.dp)
                .width(280.dp)
                .constrainAs(dog) {
                    top.linkTo(edit.bottom, margin = 0.dp)
                    start.linkTo(parent.start, margin = 0.dp)
                    end.linkTo(parent.end, margin = 50.dp)
                }
        )
        EmptyTextField(
            value = nickname.value,
            onValueChange = nickname.component2(),
            placeholder = {
                Text(
                    text = "请输入你的昵称",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp)
                )
            },
            modifier = Modifier
                .height(50.dp)
                .width(300.dp)
                .constrainAs(edit) {
                    top.linkTo(head.bottom, margin = 50.dp)
                    start.linkTo(parent.start, margin = 0.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                }
                .shadow(10.dp, RoundedCornerShape(20.dp)),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BlueButton,
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified,
                cursorColor = Color.White
            ),
            textStyle = TextStyle(
                textAlign = TextAlign.Center ,
                color = Color.White
            ),
            shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { setNickname(navController) }
            ),
            trailingIcon = {
                IconButton(onClick = { setNickname(navController) }) {
                    UnspecifiedIcon(
                        painter = painterResource(id = R.mipmap.return_register_icon),
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                    )
                }
            }
        )
    }
}


@Preview
@Composable
fun PreviewSetNickname(){
    val registerViewModel:RegisterViewModel = viewModel()
    SetNicknameScreen(
        navController = rememberNavController(),
        nickname = registerViewModel.nickname,
        setNickname = registerViewModel::setNickname
    )
}