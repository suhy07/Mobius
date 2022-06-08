package edu.fzu.mobius.ui.mailbox

import ToastMsg
import androidx.lifecycle.ViewModel
import edu.fzu.mobius.entity.Letter
import edu.fzu.mobius.network.Network
import edu.fzu.mobius.network.VerificationCodeForm

//class AnonMailBoxViewModel(application: Application) : AndroidViewModel(application) {
class AnonMailBoxViewModel: ViewModel(){
    var anonList: List<Letter> = listOf()
    var isFirst = true
    fun getAnonList() {
//        if(isFirst){
//            Network.networkThreadString(
//                requestService =  Network.service::randomAnonList,
//                body = ""
//            )
//        }
        Network.networkThread(
                requestService =  Network.service::getAnonList,
                body = "",
                code200 = {
//                    it.data.get()
                    ToastMsg(it.data.toString(),ToastType.SUCCESS);
                    ToastMsg(it.message,ToastType.SUCCESS);
                }
            )
    }

}

data class Anon(
    val id: String,

)