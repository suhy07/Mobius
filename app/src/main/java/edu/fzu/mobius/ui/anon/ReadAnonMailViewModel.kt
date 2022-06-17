package edu.fzu.mobius.ui.anon

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.network.Network

class ReadAnonMailViewModel: ViewModel() {
    var flowVisible = mutableStateOf(true)
    var otherNickname = mutableStateOf("陌生人")
    var icon = mutableStateOf("")
    var content = mutableStateOf("")
    var state = mutableStateOf(true)

    var lnkMargin =  mutableStateOf(180.dp)


    fun getInfo(id: Int,setValue: (String)->Unit){
        Network.networkThreadGet(
            requestService = Network.service::getAnonValue,
            param = id,
            code200 = { it ->
                it.data?.let { it ->
                    content.value = it["content"] as String
                    setValue(content.value)
                }
            },
        )
        Network.networkThreadGetString(
            requestService = Network.service::getHasLike,
            param = id,
            code200 = { it ->
                it.data?.let {it ->
                    flowVisible.value = !(it as Boolean)
                    if (!flowVisible.value)
                        lnkMargin.value = 0.dp
                }
            }
        )
    }

    fun flower(id: Int){
        flowVisible.value = false
        lnkMargin.value = 0.dp
        Network.networkThreadGetString(
            requestService = Network.service::addLike,
            param = id,
        )
    }

    fun reply(navController: NavController){
        singleTaskNav(navController, "write_anon_mail_screen")
    }
}