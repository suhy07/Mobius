package edu.fzu.mobius.ui.mine

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MineViewModel : ViewModel() {
    var nickname = mutableStateOf("黄埠铁牛")
    var tamp = mutableStateOf(100)
    var grow = mutableStateOf(100)
}