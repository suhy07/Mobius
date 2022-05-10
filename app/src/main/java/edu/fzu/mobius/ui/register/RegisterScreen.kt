package edu.fzu.mobius.ui.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
import edu.fzu.mobius.compose.CountdownButton
import edu.fzu.mobius.compose.EmptyTextField
import edu.fzu.mobius.theme.BlueButton
import edu.fzu.mobius.ui.common.UnspecifiedIcon


@Composable
fun     RegisterScreen(
    navController: NavController,
    phoneNumber: MutableState<String>,
    verificationCode: MutableState<String>,
    password: MutableState<String>,
    passwordRepeat: MutableState<String>,
    register: (NavController)->Unit,
    sendVerificationCode: ()->Unit
) {
    var count = remember{ mutableStateOf(60) }
    var state = remember{ mutableStateOf(true)}
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (dog,house,edit,_register) = createRefs()
        UnspecifiedIcon(
            painter = painterResource(id = R.drawable.login_house),
            modifier = Modifier
                .width(300.dp)
                .height(420.dp)
                .constrainAs(house) {
                    top.linkTo(parent.top, margin = 0.dp)
                    start.linkTo(parent.start, margin = 180.dp)
                }
        )
        Card(
            modifier = Modifier
                .height(300.dp)
                .width(300.dp)
                .constrainAs(edit) {
                    top.linkTo(dog.top, margin = 118.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            shape = RoundedCornerShape(20.dp),
            elevation = 5.dp
        ) {
            ConstraintLayout {
                val (phone,code,send,_password,_passwordRepeat) = createRefs()
                EmptyTextField(
                    value = phoneNumber.value,
                    onValueChange = phoneNumber.component2(),
                    placeholder = {
                        Text(
                            text = "请输入手机号",
                            fontSize = 12.sp
                        ) },
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp)
                        .constrainAs(phone) {
                            top.linkTo(parent.top, margin = 0.dp)
                            bottom.linkTo(parent.bottom, margin = 190.dp)
                            end.linkTo(parent.end, margin = 40.dp)
                            start.linkTo(parent.start, margin = 0.dp)
                        }
                )
                EmptyTextField(
                    value = verificationCode.value,
                    onValueChange = verificationCode.component2(),
                    placeholder = {
                        Text(
                            text = "请输入验证码",
                            fontSize = 12.sp
                        ) },
                    modifier = Modifier
                        .height(50.dp)
                        .width(120.dp)
                        .constrainAs(code) {
                            top.linkTo(parent.top, margin = 0.dp)
                            bottom.linkTo(parent.bottom, margin = 70.dp)
                            end.linkTo(parent.end, margin = 120.dp)
                            start.linkTo(parent.start, margin = 0.dp)
                        }
                )
                CountdownButton(
                    tips = "发送验证码",
                    tipsAft = "重新发送",
                    onClick = sendVerificationCode,
                    count = count,
                    state = state,
                    modifier = Modifier
                        .height(30.dp)
                        .width(100.dp)
                        .constrainAs(send) {
                            top.linkTo(parent.top, margin = 10.dp)
                            bottom.linkTo(parent.bottom, margin = 70.dp)
                            end.linkTo(parent.end, margin = 0.dp)
                            start.linkTo(parent.start, margin = 100.dp)
                        }
                )
                EmptyTextField(
                    value = password.value,
                    onValueChange = password.component2(),
                    placeholder = {
                        Text(
                            text = "请输入密码",
                            fontSize = 12.sp
                        ) },
                    modifier = Modifier
                        .height(50.dp)
                        .width(250.dp)
                        .constrainAs(_password) {
                            top.linkTo(parent.top, margin = 120.dp)
                            bottom.linkTo(parent.bottom, margin = 70.dp)
                            end.linkTo(parent.end, margin = 0.dp)
                            start.linkTo(parent.start, margin = 10.dp)
                        },
                    visualTransformation = PasswordVisualTransformation()
                )
                EmptyTextField(
                    value = passwordRepeat.value,
                    onValueChange = passwordRepeat.component2(),
                    placeholder = {
                        Text(
                            text = "请再次输入密码",
                            fontSize = 12.sp
                        ) },
                    modifier = Modifier
                        .height(50.dp)
                        .width(250.dp)
                        .constrainAs(_passwordRepeat) {
                            top.linkTo(parent.top, margin = 170.dp)
                            bottom.linkTo(parent.bottom, margin = 0.dp)
                            end.linkTo(parent.end, margin = 0.dp)
                            start.linkTo(parent.start, margin = 10.dp)
                        },
                    visualTransformation = PasswordVisualTransformation()
                )
            }
        }
        UnspecifiedIcon(
            painter = painterResource(id = R.drawable.login_dog),
            modifier = Modifier
                .height(220.dp)
                .width(200.dp)
                .constrainAs(dog) {
                    top.linkTo(parent.top, margin = 146.dp)
                    end.linkTo(parent.end, margin = 40.dp)
                }
        )
        NoShadowButton(
            onClick = { register(navController) },
            color = BlueButton,
            modifier = Modifier
                .height(50.dp)
                .width(300.dp)
                .constrainAs(_register) {
                    top.linkTo(edit.bottom, margin = 50.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            shape = RoundedCornerShape(20.dp),
            elevation = ButtonDefaults.elevation(5.dp,0.dp,0.dp)
        ) {
            Text(
                text = "注册",
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxSize(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewRegister(){
    val registerViewModel:RegisterViewModel = viewModel()
    val navController = rememberNavController()
    RegisterScreen(
        navController = navController,
        phoneNumber = registerViewModel.phoneNumber,
        verificationCode = registerViewModel.verificationCode,
        password = registerViewModel.password,
        passwordRepeat = registerViewModel.passwordRepeat,
        register = registerViewModel::register,
        sendVerificationCode = registerViewModel::sendVerificationCode
    )
}