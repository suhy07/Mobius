package edu.fzu.mobius.ui.penpal

import ToastMsg
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.entity.ReceiveProject
import edu.fzu.mobius.entity.StrangeDataProject
import edu.fzu.mobius.network.ApplyFriendFrom
import edu.fzu.mobius.network.DeleteFriendFrom
import edu.fzu.mobius.network.Network

class ReturnWritePenPalViewModel: ViewModel() {

    var floatingVisible = mutableStateOf(true)
    var penpalId = mutableStateOf<Int>(2)
    var friendId = mutableStateOf<Int>(2)
    var penpalnikename = mutableStateOf("笔友一号")
    val receivelist = mutableStateOf(listOf<ReceiveProject>())
    fun DeleteStranger(navController: NavController){
        Network.networkThreadGetString(
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
    fun getlistReceived(){
        Network.networkThreadGet(
            requestService = Network.service::getlistReceived,
            param =  friendId.value,
            code200 = {

                val maillist = arrayListOf<ReceiveProject>()
                for (s in it.data["list"] as List<Map<String,Any>>){

                    var id = 0
                    var receiverId = 0
                    var contentbrief = ""
                    var title = ""
                    var receiverNickname = ""
                    var nickName = ""

                    if(s["id"] != null){
                        id = (s["id"] as Double).toInt()
                    }
                    if(s["receiverId"] != null){
                        receiverId = (s["receiverId"] as Double).toInt()
                    }
                    if(s["contentbrief"] != null){
                        contentbrief = s["contentbrief"] as String
                    }
                    if(s["title"] != null){
                        title = s["title"] as String
                    }
                    if(s["receiverNickname"] != null){
                        receiverNickname = s["receiverNickname"] as String
                    }
                    if(s["icon"] != null){
                        nickName = s["nickName"] as String
                    }
                    val receiveProject = ReceiveProject(id, receiverId,contentbrief,title, receiverNickname, nickName)
                    maillist.add(receiveProject)
                }
                receivelist.value = maillist
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