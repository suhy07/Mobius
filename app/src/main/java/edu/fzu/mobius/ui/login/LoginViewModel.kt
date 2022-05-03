package edu.fzu.mobius.ui.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.constraintlayout.compose.override
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import java.util.*

class LoginViewModel:ViewModel() {
    var phoneNumber = mutableStateOf("")
    var verificationCode = mutableStateOf("")
    var password = mutableStateOf("")
    var count =  mutableStateOf(60)
    fun login(navController: NavController){
        navController.navigate("mailbox_screen")
        sendVerificationCode()
    }

    fun sendVerificationCode(){
    }
}