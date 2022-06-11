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

    val letterValue = mutableStateOf("")

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




    //    fun onEditItemChange(item: LineItem) {
//
//        Log.d("TAGTAG","edit")
////        val currentItem = requireNotNull(currentEditItem)
////        require(currentItem.id == item.id) {
//            "You can only change an item with the same id as currentEditItem"
//            Log.d("TAGTAG","edit1")
//            var (value,id,readOnly) = item
//            getIndex(item)
//            Log.d("TAGTAG","currentIndex$currentEditPosition")
//            val index = currentEditPosition
//            Log.d("TAGTAG","value:"+value)
//            if(value.isEmpty() && index!=0) {
//                LineItems[currentEditPosition] = item
//                changeOnlyRead(index-1 ,false)
////                                onRemoveItem(items[index])
//                count--
//                removeItem(LineItems[index])
//                Log.d("TAGTAG", (index-1).toString()+LineItems[index-1].readonly)
//            }
//            else if(value.length>20) {
//                if(index == count){
//                    count++
//                    addItem(LineItem("",false, UUID.randomUUID(),count))
//                }
//                Log.d("TAGTAG", value.length.toString())
//                LineItems[currentEditPosition].value = item.value.substring(0,20)
//                val nextItem = LineItems[currentEditPosition + 1].copy()
//                nextItem.value = item.value[20].toString() +nextItem.value
////                LineItems.set(currentEditPosition+1,nextItem)
//                onEditItemChange(nextItem)
//                changeOnlyRead(index ,true)
//            }else{
//                LineItems[currentEditPosition] = item
//            }
////        }
//
//
//    }
}
