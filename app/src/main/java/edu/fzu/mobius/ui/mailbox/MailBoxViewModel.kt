package edu.fzu.mobius.ui.mailbox

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MailBoxViewModel: ViewModel() {

    var cardVisible = mutableStateOf(false)
    var signInVisible = mutableStateOf(false)
    var floatingVisible = mutableStateOf(true)
    var expanded = mutableStateOf(false)
    var time = mutableStateOf("21:00")
    val items = listOf("00:00","01:00","21:00","00:00","01:00","21:00","00:00","01:00","21:00")

}