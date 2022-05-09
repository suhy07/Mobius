package edu.fzu.mobius.ui.draft

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import edu.fzu.mobius.entity.Draft

class DraftViewModel:ViewModel() {
    val drafts = mutableStateListOf<Draft>()

    init {
        repeat(5){
            drafts.add(
                Draft(
                    0,
                    "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                    "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                    "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                    "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                    "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                    "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                    "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                    "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                    "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                    "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                    "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                    "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_"
                )
            )
        }
    }
}