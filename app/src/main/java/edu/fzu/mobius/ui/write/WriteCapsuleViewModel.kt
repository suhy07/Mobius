package edu.fzu.mobius.ui.write

import ToastMsg
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.network.*


class WriteCapsuleViewModel:ViewModel() {
    var arriveTime = mutableStateOf("1")
    var content = mutableStateOf("")
    var contentId = mutableStateOf(2)
    var receiverId = mutableStateOf(2)
    var title = mutableStateOf("")
    fun sendWriteCapsule(navController: NavController){
            Network.networkThread(
                requestService = Network.service::sendCapsule,
                body = sendCapsule(arriveTime =arriveTime.value, content = content.value, contentId = contentId.value,
                receiverId = receiverId.value,title = title.value),
                code200 = {
//                    Network.token = it.data["token"] as String

                    singleTaskNav(navController, "capsule_success_screen")
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
