package edu.fzu.mobius.ui.feedback

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.entity.Draft
import edu.fzu.mobius.navigation.singleTaskNav
import edu.fzu.mobius.network.FeedbackForm
import edu.fzu.mobius.network.Network
import edu.fzu.mobius.network.Network.*
import edu.fzu.mobius.network.RegisterForm

class FeedbackViewModel: ViewModel() {
    val feedbackValue = mutableStateOf("")
    fun feedback(navController: NavController){
        val feedbackForm = FeedbackForm(feedbackValue.value)
        Network.networkThreadString(
            requestService = Network.service::feedback,
            body = feedbackForm,
            router = {
                singleTaskNav(navController,"mine_screen")
            },
        )
    }
}