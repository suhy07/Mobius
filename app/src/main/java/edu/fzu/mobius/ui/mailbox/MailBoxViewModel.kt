package edu.fzu.mobius.ui.mailbox

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.fzu.mobius.entity.Letter
import edu.fzu.mobius.entity.LetterType
import edu.fzu.mobius.global.GlobalMem
import edu.fzu.mobius.network.Network
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class MailBoxViewModel: ViewModel() {

    var cardVisible = mutableStateOf(false)
    var signInVisible = mutableStateOf(false)
    var floatingVisible = mutableStateOf(true)
    var expanded = mutableStateOf(false)

    val letters =  mutableStateListOf<Letter>()


    fun getSentMailBox(){
        Network.networkThread(
            requestService = Network.service::getCapsuleSent,
            body = "",
            code200 = {
                (it.data["list"] as ArrayList<Map<String, Any>>)?.let { it ->
                    for (map in it){
                        val letter = Letter(
                            userNickname = "胶囊用户",
                            abstract = map["contentBrief"] as String,
                            otherNickname = GlobalMem.NICK_NAME,
                            type = LetterType.TIME,
                            id = (map["id"] as Double).toInt(),
                            time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS").parse(map["createTime"] as String)
                        )
                        letters.add(letter)
                    }
                    letters.sortBy { it.time }
                }
            }
        )
        Network.networkThread(
            requestService = Network.service::getAnonSent,
            body = "",
            code200 = {
                (it.data["list"] as ArrayList<Map<String, Any>>)?.let { it ->
                    for (map in it){
                        val letter = Letter(
                            userNickname = "陌生人",
                            abstract = map["contentBrief"] as String,
                            otherNickname = GlobalMem.NICK_NAME,
                            type = LetterType.ANON,
                            id = (map["id"] as Double).toInt(),
                            time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS").parse(map["createTime"] as String)
                        )
                        letters.add(letter)
                    }
                    letters.sortBy { it.time }
                }
            }
        )
    }

    fun getReceivedMailBox(){
        letters.clear()
        Network.networkThreadArray(
            requestService =  Network.service::getAnonList,
            body = "",
            code200 = {
                for(item in it.data){
                    val letter = Letter(
                        userNickname = GlobalMem.NICK_NAME,
                        abstract = item["contentBrief"] as String,
                        otherNickname = "陌生人",
                        type = LetterType.ANON,
                        id = (item["id"] as Double).toInt(),
                        time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS").parse(item["createTime"] as String)
                    )
                    letters.add(letter)
                }
                letters.sortBy { it.time }
            }
        )
        Network.networkThread(
            requestService =  Network.service::getApplyList,
            body = "",
            code200 = {
                for(item in it.data["list"] as ArrayList<Map<String, Any>>){
                    val letter = Letter(
                        userNickname = GlobalMem.NICK_NAME,
                        abstract = "这是一条好友申请",
                        otherNickname = item["nickname"] as String,
                        type = LetterType.INVITE,
                        id = (item["friendId"] as Double).toInt(),
                        time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS").parse(item["createTime"] as String)
                    )
                    letters.add(letter)
                }
                letters.sortBy { it.time }
            }
        )
        Network.networkThread(
            requestService =  Network.service::getCapsuleList,
            body = "",
            code200 = {
                for(map in it.data["list"] as ArrayList<Map<String, Any>>){
                    val letter = Letter(
                        userNickname = GlobalMem.NICK_NAME,
                        abstract = map["contentBrief"] as String,
                        otherNickname = "胶囊用户",
                        type = LetterType.TIME,
                        id = (map["id"] as Double).toInt(),
                        time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS").parse(map["arriveTime"] as String)
                    )
                    letters.add(letter)
                }
                letters.sortBy { it.time }
            }
        )
    }

}