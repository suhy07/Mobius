package edu.fzu.mobius.ui.mailbox

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.fzu.mobius.global.GlobalMem

class MailBoxViewModel: ViewModel() {

    var cardVisible = mutableStateOf(false)
    var signInVisible = mutableStateOf(false)
    var floatingVisible = mutableStateOf(true)
    var expanded = mutableStateOf(false)

    fun getSentMailBox(){

    }

}