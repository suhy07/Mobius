package edu.fzu.mobius.ui.penpal

import ToastMsg
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.JsonParser
import edu.fzu.mobius.entity.StrangeDataProject
import edu.fzu.mobius.entity.TestData
import edu.fzu.mobius.network.*


class PenPalViewModel:ViewModel() {
    var nickname = mutableStateOf("")
    var strangenickname = mutableStateOf("")
    var strangeId = mutableStateOf<Int>(2)
    var nickname1 = mutableStateOf<Int>(2)//测试

    val friendlist = mutableStateOf(listOf<TestData.Data.Project>())
    val strangelist = mutableStateOf(listOf<StrangeDataProject>())

    fun PenPalList(){

//            friendlist=Penpal.friendlist
            Network.networkThreadGet(
                requestService = Network.service::setFriendlist,
                param = nickname.value,
                code200 = {
                    val jsonParser = JsonParser()
                    val jsonElement = jsonParser.parse(it.data.toString())
                   val bean = Gson().fromJson(jsonElement, TestData.Data::class.javaObjectType)
                    val result = bean.list
                    friendlist.value = result as ArrayList<TestData.Data.Project>
                    nickname1.value = bean.pageNum
                    Log.e("SDDD",result.toString())
                },
            )

        Log.e("ACDD",nickname1.value.toString())
    }
    fun AddPenPalList() {
        Network.networkThreadGet(
            requestService = Network.service::setStrangelist,
            param = strangenickname.value,
            code200 = {
//                val jsonParser = JsonParser()
//                val jsonElement = jsonParser.parse(it.data.toString())
//                val bean = Gson().fromJson(jsonElement, StrangeData.Data::class.javaObjectType)
//                val result = bean.list
                val strangelist_ = arrayListOf<StrangeDataProject>()
                for (s in it.data["list"] as List<Map<String,Any>>){
                    var nickname = ""
                    var id = 0
                    var growValue = 0
                    var icon = ""
                    if (s["nickname"] != null){
                        nickname = s["nickname"] as String
                    }
                    if(s["id"] != null){
                        id = (s["id"] as Double).toInt()
                    }
                    if(s["growValue"] != null){
                        growValue = (s["growValue"] as Double).toInt()
                    }
                    if(s["icon"] != null){
                        icon = s["icon"] as String
                    }
                    val strangeDataProject = StrangeDataProject(id, nickname, growValue, icon)
                    strangelist_.add(strangeDataProject)
                }
                strangelist.value = strangelist_
//                nickname1.value = it.data["total"] as Int

//                Log.e("SDDD12",it.toString())
//                Log.e("SDDD1",strangelist.toString()+"wuy"+nickname1.value)
            },
        )
    }
    fun AddStranger(navController: NavController){
        Network.networkThreadGet(
            requestService = Network.service::applyFriend,
            param =  strangeId.value,
            code200 = {

                PopWindows.postValue(
                    ToastMsg(
                        value = "添加好友成功",
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


