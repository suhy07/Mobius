package edu.fzu.mobius.ui.mail

import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WriteMailViewModel : ViewModel() {

    private var currentEditPosition by mutableStateOf(0)

    var letterValue = mutableStateOf("")

    var otherNickName by mutableStateOf("陌生人")
        private set

    private val defaultLen = 10

    private val maxLen = 280


    var LineItems = mutableStateListOf<LineItem>()
        private set

    init {
        repeat(defaultLen){
            LineItems.add(LineItem(""))
        }
    }

    private fun setCurrentIndex(item: LineItem){
        for ( i in LineItems){
            if(i.id == item.id)
                currentEditPosition = LineItems.indexOf(i)
        }
    }


    private fun cutLine(){
        LineItems.clear()
        var t = ""
        for (i in letterValue.value) {
            val pFont = Paint()
            val rect = Rect()
            t += i
            pFont.getTextBounds(t, 0, t.length, rect)
            if (rect.width() > maxLen) {
                LineItems.add(LineItem(t))
                t = ""
            }
        }
        LineItems.add(LineItem(t))
        val diff = defaultLen - LineItems.size
        when{
            diff > 0 -> repeat(diff){
                LineItems.add(LineItem(""))
            }
        }
    }

    private fun setLetterValueFromRemember() {
        var t = ""
        for(i in LineItems){
            t+=i.value
        }
        letterValue.value = String(t.toCharArray())
    }

    fun onEditItemChange(item: LineItem) {
        setCurrentIndex(item)
        LineItems[currentEditPosition] = item
        setLetterValueFromRemember()
        cutLine()
    }

    fun setLetterValue(value: String){
        letterValue.value = value
        cutLine()
    }

}
