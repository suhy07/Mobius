package edu.fzu.mobius.ui.mine

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.fzu.mobius.network.Network

class MineViewModel : ViewModel() {

    var nickname = mutableStateOf("黄埠铁牛")
    var stamp = mutableStateOf(100)
    var grow = mutableStateOf(100)

    var phone = mutableStateOf("")
    var mood = mutableStateOf(1)
    var icon = mutableStateOf("")
    var status = mutableStateOf(0)

    fun getUserInfo() {
        Network.networkThread(
            requestService = Network.service::getUserInfo,
            body = "",
            code200 = {
                phone.value = it.data["phone"] as String
                nickname.value = it.data["nickname"] as String
                mood.value =  (it.data["mood"] as Double).toInt()
                grow.value = (it.data["growValue"] as Double).toInt()
                status.value = (it.data["status"] as Double).toInt()
            }
        )
    }


}