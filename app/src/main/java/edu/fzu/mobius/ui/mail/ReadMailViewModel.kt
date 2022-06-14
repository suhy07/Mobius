package edu.fzu.mobius.ui.mail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class ReadMailViewModel: ViewModel() {
    var cardVisible = mutableStateOf(false)
    var otherNickname = mutableStateOf("")
    var icon = mutableStateOf("")
    var content = mutableStateOf("")
    var state = mutableStateOf(true)


    fun flower(){

    }

    fun reply(navController: NavController){

    }
}