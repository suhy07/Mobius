package edu.fzu.mobius.ui.mail

import ToastMsg
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.network.*


class WriteCapsuleViewModel:ViewModel() {
    var arriveTime = mutableStateOf("1")
    var content = mutableStateOf("")
    var receiverId = mutableStateOf(0)
    var title = mutableStateOf("")
    var selectList: MutableList<String> = mutableListOf("我自己")
    var selectIdList: MutableList<Int> = mutableListOf(0)
    //存储信件到达时间
    val return_mDate = mutableStateOf("")

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
