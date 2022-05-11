package edu.fzu.mobius.ui.login

import ToastMsg
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel:ViewModel() {
    var phoneNumber = mutableStateOf("")
    var verificationCode = mutableStateOf("")
    var password = mutableStateOf("")
    var state = mutableStateOf(true)
    fun login(navController: NavController){
        Thread{
            if(!state.value){
                Network.service.logInByPassword(LoginPasswordForm(phone = phoneNumber.value, password = password.value))
                    .enqueue(object : Callback<LogInBackData> {
                        override fun onResponse(
                            call: Call<LogInBackData>,
                            response: Response<LogInBackData>
                        ) {
                            response.body()?.let { it ->
                                when(it.code){
                                    200 -> singleTaskNav(navController,"mailbox_screen")
                                    else -> {
                                        PopWindows.postValue(
                                            ToastMsg(
                                                value = it.code.toString()+" "+it.message,
                                                type = ToastType.ERROR
                                            )
                                        )
                                    }
                                }
                            }
                        }
                        override fun onFailure(call: Call<LogInBackData>, t: Throwable) {
                            PopWindows.postValue(
                                ToastMsg(
                                    value = t.localizedMessage,
                                    type = ToastType.ERROR
                                )
                            )
                        }
                    })
            }else{
                Network.service.logInByCode(LoginCodeForm(phone = phoneNumber.value, code = verificationCode.value))
                    .enqueue(object : Callback<LogInBackData> {
                        override fun onResponse(
                            call: Call<LogInBackData>,
                            response: Response<LogInBackData>
                        ) {
                            response.body()?.let { it ->
                                when(it.code){
                                    200 -> navController.navigate("mailbox_screen")
                                    else -> {
                                        PopWindows.postValue(
                                            ToastMsg(
                                                value = it.code.toString()+" "+it.message,
                                                type = ToastType.ERROR
                                            )
                                        )
                                    }
                                }
                            }
                        }
                        override fun onFailure(call: Call<LogInBackData>, t: Throwable) {
                            PopWindows.postValue(
                                ToastMsg(
                                    value = t.localizedMessage,
                                    type = ToastType.ERROR
                                )
                            )
                        }
                    })
            }
        }.start()
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
            Thread {
                Network.service.loginSendVerificationCode(VerificationCodeForm(phone = phoneNumber.value))
                    .enqueue(object : Callback<LogInBackData> {
                        override fun onResponse(
                            call: Call<LogInBackData>,
                            response: Response<LogInBackData>
                        ) {
                            response.body()?.let { it ->
                                when (it.code) {
                                    200 -> {
                                        PopWindows.postValue(
                                            ToastMsg(
                                                value = it.code.toString() + " " + it.message,
                                                type = ToastType.SUCCESS
                                            )
                                        )
                                    }
                                    else -> {
                                        PopWindows.postValue(
                                            ToastMsg(
                                                value = it.code.toString() + " " + it.message,
                                                type = ToastType.ERROR
                                            )
                                        )
                                    }
                                }
                            }
                        }

                        override fun onFailure(call: Call<LogInBackData>, t: Throwable) {
                            PopWindows.postValue(
                                ToastMsg(
                                    value = t.localizedMessage,
                                    type = ToastType.ERROR
                                )
                            )
                        }
                    })
            }.start()
        }
    }
}
