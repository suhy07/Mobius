package edu.fzu.mobius.ui.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.navigation.singleTaskNav

class RegisterViewModel:ViewModel() {
    var phoneNumber = mutableStateOf("")
    var verificationCode = mutableStateOf("")
    var password = mutableStateOf("")
    var passwordRepeat = mutableStateOf("")
    var nickname = mutableStateOf("")

    fun register(navController: NavController){
        singleTaskNav(navController,"set_nickname_screen")
    }

    fun sendVerificationCode(){

    }

    fun setNickname(navController: NavController){
        singleTaskNav(navController,"mailbox_screen")
    }
}