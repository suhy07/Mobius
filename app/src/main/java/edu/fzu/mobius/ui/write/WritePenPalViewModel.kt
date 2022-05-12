package edu.fzu.mobius.ui.write

import ToastMsg
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.network.*


class WritePenPalViewModel:ViewModel() {
    var applyUserId = mutableStateOf(2)
    var friendlist = mutableListOf<Project>()
    fun WritePenPalApply(navController: NavController){
            Network.networkThread(
                requestService = Network.service::applyFriend,
                body = ApplyFriendFrom(applyUserId =applyUserId.value),
                code200 = {
                    friendlist = it.data["list"] as MutableList<Project>
//                    Network.token = it.data["token"] as String
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
