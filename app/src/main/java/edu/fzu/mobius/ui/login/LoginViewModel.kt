package edu.fzu.mobius.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.navigation.singleTaskNav

class LoginViewModel:ViewModel() {
    var phoneNumber = mutableStateOf("")
    var verificationCode = mutableStateOf("")
    var password = mutableStateOf("")
    fun login(navController: NavController){
        singleTaskNav(navController, "mailbox_screen")
        sendVerificationCode()
    }

    fun sendVerificationCode(){
    }
}