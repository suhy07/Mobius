package edu.fzu.mobius.ui.feedback

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.fzu.mobius.entity.Draft

class FeedbackViewModel: ViewModel() {
    val feedbackValue = mutableStateOf("")
    fun feedback(){

    }
}