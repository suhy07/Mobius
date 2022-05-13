package edu.fzu.mobius.ui.write

import ToastMsg
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.network.*
import edu.fzu.mobius.ui.penpal.Penpal


class WriteCapsuleViewModel:ViewModel() {
    var arriveTime = mutableStateOf("1")
    var content = mutableStateOf("")
    var contentId = mutableStateOf(2)
    var receiverId = mutableStateOf(0)
    var title = mutableStateOf("")
    var selectList: MutableList<String> = mutableListOf("我自己")
    var selectIdList: MutableList<Int> = mutableListOf(0)

    fun sendWriteCapsule(navController: NavController){
            Network.networkThreadString(
                requestService = Network.service::sendCapsule,
                body = SendCapsuleForm(arriveTime =arriveTime.value, content = content.value,
                receiverId = receiverId.value,title = title.value),

                router = {
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
