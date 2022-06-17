package edu.fzu.mobius.ui.penpal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.network.Network

class InviteViewModel: ViewModel() {
    val nickname = mutableStateOf("")
    val icon = mutableStateOf("")

    fun getInviteInfo(id: Int){

    }

    fun applyInvite(navController: NavController, id: Int){
        Network.networkThreadString(
            requestService = Network.service::applyFriend,
            body = id,
            router = {
                singleTaskNav(navController, "invite_success_screen")
            }
        )
    }

}