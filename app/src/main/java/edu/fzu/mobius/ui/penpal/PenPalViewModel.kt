package edu.fzu.mobius.ui.penpal

import ToastMsg
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import edu.fzu.mobius.entity.TestData
import edu.fzu.mobius.network.*


class PenPalViewModel:ViewModel() {
    var nickname = mutableStateOf("h")
    var nickname1 = mutableStateOf<Int>(2)
    var friendlist = mutableListOf<Project>()

    fun PenPalList(navController: NavController){
            Network.networkThreadget(
                requestService = Network.service::setFriendlist,
                param = nickname.value,
                code200 = {
                    val jsonParser = JsonParser()
                    val jsonElement = jsonParser.parse(it.data.toString())
                   val bean = Gson().fromJson(jsonElement,Data::class.javaObjectType)
                    val result = bean.list
                    friendlist = result as MutableList<Project>
                    nickname1.value = bean.pageNum
                    Log.e("SDDD",result.toString())
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

        Log.e("ACDD",nickname1.value.toString())
    }

}


