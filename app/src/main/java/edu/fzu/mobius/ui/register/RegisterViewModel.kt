package edu.fzu.mobius.ui.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class RegisterViewModel:ViewModel() {
    var phoneNumber = mutableStateOf("")
    var verificationCode = mutableStateOf("")
    var password = mutableStateOf("")
    var passwordRepeat = mutableStateOf("")
    var nickname = mutableStateOf("")

    fun register(navController: NavController){
        navController.navigate("set_nickname_screen")
    }

    fun sendVerificationCode(){

    }

    fun setNickname(navController: NavController){
        navController.navigate("mailbox_screen")
    }
}