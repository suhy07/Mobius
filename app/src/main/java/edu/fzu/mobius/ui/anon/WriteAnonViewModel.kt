package edu.fzu.mobius.ui.anon

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.network.Network
import edu.fzu.mobius.network.PostAnonForm
import edu.fzu.mobius.network.RequestService

class WriteAnonViewModel: ViewModel() {

    fun sentAnon(content:String, mood:Float, navController: NavController){
        var moodLevel = (mood/20 + 1).toInt()
        val postAnonForm = PostAnonForm(
            content = content,
            contentId = 0,
            moodLevel = moodLevel
        )
        Network.networkThreadString(
            requestService = Network.service::postAnonLetter,
            body = postAnonForm,
            code200 = {
                singleTaskNav(navController, "anon_success_screen")
            }
        )

    }
}