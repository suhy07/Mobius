package edu.fzu.mobius.ui.password

import ToastMsg
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.network.*

class ChangePasswordViewModel : ViewModel() {
    var phoneNumber = mutableStateOf("")
    var verificationCode = mutableStateOf("")
    var password = mutableStateOf("")
    var passwordRepeat = mutableStateOf("")
    var nickname = mutableStateOf("")

    fun change(navController: NavController){
        when{
            phoneNumber.value.isEmpty() -> {
                PopWindows.postValue(
                    ToastMsg(
                        value = "WARM 手机号不嫩为空",
                        type = ToastType.WARM
                    )
                )
            }
            verificationCode.value.isEmpty() -> {
                PopWindows.postValue(
                    ToastMsg(
                        value = "WARM 验证码未填写",
                        type = ToastType.WARM
                    )
                )
            }
            password.value.isEmpty() -> {
                PopWindows.postValue(
                    ToastMsg(
                        value = "WARM 密码不能为空",
                        type = ToastType.WARM
                    )
                )
            }
            passwordRepeat.value.isEmpty() -> {
                PopWindows.postValue(
                    ToastMsg(
                        value = "WARM 请再次输入密码",
                        type = ToastType.WARM
                    )
                )
            }
            !password.value.equals(passwordRepeat.value) -> {
                PopWindows.postValue(
                    ToastMsg(
                        value = "WARM 两次密码不相等",
                        type = ToastType.WARM
                    )
                )
            }
            else -> {
                Network.networkThreadString(
                    requestService = Network.service::change,
                    body = ChangePasswordForm(
                        phone = phoneNumber.value,
                        code = verificationCode.value,
                        password = password.value
                    ),
                    router = {
                        singleTaskNav(navController,"mailbox_screen")
                    },
                )
            }
        }
    }
    fun sendVerificationCode(){

        if(phoneNumber.value.isEmpty()){
            PopWindows.postValue(
                ToastMsg(
                    value = "ERROR 手机号不能为空",
                    type = ToastType.ERROR
                )
            )
        }else {
            Network.networkThreadString(
                requestService = Network.service::changePasswordSendVerificationCode,
                body = VerificationCodeForm(phone = phoneNumber.value),
            )
        }
    }
}