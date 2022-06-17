package edu.fzu.mobius.ui.draft

import ToastMsg
import android.widget.PopupWindow
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.entity.Draft
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.network.Network
import edu.fzu.mobius.network.SaveDraftForm

class DraftViewModel:ViewModel() {
    val drafts = mutableStateListOf<Draft>()
    val content = mutableStateOf("")

    fun getDrafts(){
        Network.networkThread(
            requestService = Network.service::getDraftList,
            body = 1,
            code200 = {
                drafts.clear()
                (it.data["list"] as ArrayList<Map<String,Any>>)?.let {
                    for(map in it){
                        val draft = Draft(
                            id = (map["id"] as Double).toInt(),
                            value = map["content"] as String
                        )
                        drafts.add(draft)
                    }
                }
            }
        )
    }

    fun getDraft(id: Int){
        Network.networkThread(
            requestService = Network.service::getDraftValue,
            body = id,
            code200 = {
                content.value = it.data["content"] as String
            }
        )
    }

    fun saveDraft(id: Int, content: String, navController: NavController){
        val saveDraftForm = SaveDraftForm(
            content = content,
            title = "草稿",
            contentId = id
        )
        Network.networkThreadString(
            requestService = Network.service::saveDraft,
            body = saveDraftForm,
            router = {
                singleTaskNav(navController, "drafts_screen")
            }
        )
    }

}