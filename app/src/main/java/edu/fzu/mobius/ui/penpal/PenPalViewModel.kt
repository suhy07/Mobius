package edu.fzu.mobius.ui.penpal

import ToastMsg
import android.util.Log
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


class PenPalViewModel:ViewModel() {
    var phoneNumber = mutableStateOf("")
    var verificationCode = mutableStateOf("")
    var password = mutableStateOf("")
    var state = mutableStateOf(true)
    fun PenPalList(navController: NavController){
        if(phoneNumber.value == "1234"){
            singleTaskNav(navController,"mailbox_screen")
        }
        if(!state.value){
            Network.networkThread(
                requestService = Network.service::logInByPassword,
                body = LoginPasswordForm(phone = phoneNumber.value, password = password.value),
                code200 = {
                    singleTaskNav(navController, "mailbox_screen")
                    Network.token = it.data["token"] as String
                },
                codeElse = {
                    PopWindows.postValue(
                        ToastMsg(
                            value = it.code.toString()+" "+it.message,
                            type = ToastType.ERROR
                        )
                    )
                },
            )
        }
    }

}
