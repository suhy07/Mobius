package edu.fzu.mobius.ui.penpal

import ToastMsg
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.network.ApplyFriendFrom
import edu.fzu.mobius.network.DeleteFriendFrom
import edu.fzu.mobius.network.Network

class ReturnWritePenPalViewModel: ViewModel() {

    var floatingVisible = mutableStateOf(true)
    var penpalId = mutableStateOf<Int>(2)
    var penpalnikename = mutableStateOf("笔友一号")
    fun DeleteStranger(navController: NavController){
        Network.networkThreadGet(
            requestService = Network.service::applyFriend,
            param =  penpalId.value,
            code200 = {

                PopWindows.postValue(
                    ToastMsg(
                        value = "删除好友成功",
                        type = ToastType.SUCCESS
                    )
                )
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