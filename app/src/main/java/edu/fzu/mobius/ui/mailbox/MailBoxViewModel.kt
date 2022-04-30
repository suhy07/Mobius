package edu.fzu.mobius.ui.mailbox

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MailBoxViewModel: ViewModel() {

    var time = mutableStateOf("21:00")
    val items = listOf("00:00","01:00","21:00","00:00","01:00","21:00","00:00","01:00","21:00")
}