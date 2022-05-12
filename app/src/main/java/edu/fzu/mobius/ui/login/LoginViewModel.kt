package edu.fzu.mobius.ui.login

import ToastMsg
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.network.*
import edu.fzu.mobius.ui.mine.MineViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel:ViewModel() {
    var phoneNumber = mutableStateOf("")
    var verificationCode = mutableStateOf("")
    var password = mutableStateOf("")
    var state = mutableStateOf(true)
    fun login(navController: NavController){
        if(phoneNumber.value == "1234"){
            singleTaskNav(navController,"mailbox_screen")
        }
        if(!state.value){
            Network.networkThread(
                requestService = Network.service::logInByPassword,
                body = LoginPasswordForm(phone = phoneNumber.value, password = password.value),
                router = {
                    singleTaskNav(navController, "mailbox_screen")
                    Network.token = it.data["token"] as String
                }
            )
        }else{
            Network.networkThread(
                requestService = Network.service::logInByCode,
                body = LoginCodeForm(phone = phoneNumber.value, code = verificationCode.value),
                router = {
                    singleTaskNav(navController, "mailbox_screen")
                    Network.token = it.data["token"] as String
                },
            )
        }
    }
    fun sendVerificationCode() {
        if(phoneNumber.value.isEmpty()){
            PopWindows.postValue(
                ToastMsg(
                    value = "ERROR 手机号不能为空",
                    type = ToastType.ERROR
                )
            )
        }else{
            Network.networkThread(
                requestService =  Network.service::loginSendVerificationCode,
                body = VerificationCodeForm(phone = phoneNumber.value),
            )
        }
    }
}
