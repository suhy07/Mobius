package edu.fzu.mobius.ui.mail

import ToastMsg
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.network.*


class WritePenPalViewModel:ViewModel() {
//    var applyUserId = mutableStateOf(2)
//    var friendlist = mutableListOf<TestData.Data.Project>()
    var content = mutableStateOf("")
    var receiverId = mutableStateOf(0)
    var title = mutableStateOf("")

//    var applyUserId = mutableStateOf(2)
//    var friendlist = mutableListOf<TestData.Data.Project>()

    fun sendPenPalMail(navController: NavController) {
        Network.networkThreadString(
            requestService = Network.service::sendCapsule,
            body = SendPenForm(receiverId = receiverId.value, content = content.value,
                title = title.value),

            router = {
                singleTaskNav(navController, "send_penpal_success_screen")
            },
            codeElse = {
                PopWindows.postValue(
                    ToastMsg(
                        value = it.code.toString() + " " + it.message,
                        type = ToastType.ERROR
                    )
                )
            },
        )
//    fun WritePenPalApply(navController: NavController){
//            Network.networkThread(
//                requestService = Network.service::applyFriend,
//                body = ApplyFriendFrom(applyUserId =applyUserId.value),
//                code200 = {
//                    friendlist = it.data["list"] as MutableList<TestData.Data.Project>
////                    Network.token = it.data["token"] as String
//                },
//                codeElse = {
//                    PopWindows.postValue(
//                        ToastMsg(
//                            value = it.code.toString()+" "+it.message,
//                            type = ToastType.ERROR
//                        )
//                    )
//                },
//            )
//    }
    }
}
