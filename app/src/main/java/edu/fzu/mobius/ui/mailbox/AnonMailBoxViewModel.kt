package edu.fzu.mobius.ui.mailbox

import androidx.lifecycle.ViewModel
import edu.fzu.mobius.entity.Letter
import edu.fzu.mobius.entity.LetterType
import edu.fzu.mobius.global.GlobalMem
import edu.fzu.mobius.network.Network

//class AnonMailBoxViewModel(application: Application) : AndroidViewModel(application) {
class AnonMailBoxViewModel: ViewModel(){
    var anonList: List<Letter> = AnonMailBoxViewModel.anonList
    var isFirst = true
    companion object{
        var anonList: ArrayList<Letter> = arrayListOf()
        fun getAnonList() {
//        if(isFirst){
//            Network.networkThreadString(
//                requestService =  Network.service::randomAnonList,
//                body = ""
//            )
//         }else
            Network.networkThreadArray(
                requestService =  Network.service::getAnonList,
                body = "",
                code200 = {
                    anonList.clear()
                    for(item in it.data){
                        val letter = Letter(
                            userNickname = GlobalMem.NICK_NAME,
                            abstract = item["contentBrief"] as String,
                            otherNickname = "陌生人",
                            type = LetterType.ANON,
                            id = (item["id"] as Double).toInt()
                        )
                        anonList.add(letter)
                    }
                }
            )
        }
    }
}