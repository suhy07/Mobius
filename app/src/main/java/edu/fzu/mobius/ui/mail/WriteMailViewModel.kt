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

    private val defaultLen = 10

    private val maxLen = 180


    var lineItems = mutableStateListOf<lineItem>()
        private set

    init {
        repeat(defaultLen){
            lineItems.add(lineItem(""))
        }
    }

    private fun setCurrentIndex(item: lineItem){
        for ( i in lineItems){
            if(i.id == item.id)
                currentEditPosition = lineItems.indexOf(i)
        }
    }

    fun onEditItemChange(item: lineItem) {
        setCurrentIndex(item)
        lineItems.set(currentEditPosition, item)
        val letterValue = getLetterValue()
        lineItems.clear()
        var t = ""
        for (i in letterValue) {
            val pFont = Paint()
            val rect = Rect()
            t += i
            pFont.getTextBounds(t, 0, t.length, rect)
            if (rect.width() > maxLen) {
                lineItems.add(lineItem(t))
                t = ""
            }
        }
        lineItems.add(lineItem(t))
        val diff = defaultLen - lineItems.size
        when{
            diff > 0 -> repeat(diff){
                lineItems.add(lineItem(""))
            }
        }
    }

    private fun getLetterValue():String{
        var t = ""
        for(i in lineItems){
            t+=i.value
        }
        return t
    }

    //    fun onEditItemChange(item: lineItem) {
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
//                lineItems[currentEditPosition] = item
//                changeOnlyRead(index-1 ,false)
////                                onRemoveItem(items[index])
//                count--
//                removeItem(lineItems[index])
//                Log.d("TAGTAG", (index-1).toString()+lineItems[index-1].readonly)
//            }
//            else if(value.length>20) {
//                if(index == count){
//                    count++
//                    addItem(lineItem("",false, UUID.randomUUID(),count))
//                }
//                Log.d("TAGTAG", value.length.toString())
//                lineItems[currentEditPosition].value = item.value.substring(0,20)
//                val nextItem = lineItems[currentEditPosition + 1].copy()
//                nextItem.value = item.value[20].toString() +nextItem.value
////                lineItems.set(currentEditPosition+1,nextItem)
//                onEditItemChange(nextItem)
//                changeOnlyRead(index ,true)
//            }else{
//                lineItems[currentEditPosition] = item
//            }
////        }
//
//
//    }
}
