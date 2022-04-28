package edu.fzu.mobius.ui.mail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.*

class WriteMailViewModel : ViewModel() {

    private var count by mutableStateOf(0)
    private var currentEditPosition by mutableStateOf(0)

    var lineItems = mutableStateListOf<lineItem>()
        private set

    init {
        var item = lineItem("", false , UUID.randomUUID(),0 )
        lineItems.add(item)
    }

    val currentEditItem: lineItem?
        get() = lineItems.getOrNull(currentEditPosition)

    fun addItem(item: lineItem) {
        lineItems.add(item)
    }

    fun changeOnlyRead(index : Int, state : Boolean){
        lineItems[index].readonly = state
    }

    fun removeItem(item: lineItem) {
        lineItems.remove(item)
        onEditDone() // don't keep the editor open when removing items
    }

    fun onEditItemSelected(item: lineItem) {
        Log.d("TAGTAG",item.toString())
        currentEditPosition = lineItems.indexOf(item)
    }

    fun onEditDone() {
        currentEditPosition = -1
    }

    fun getIndex(item: lineItem){
        for ( i in lineItems){
            if(i.id == item.id)
                currentEditPosition = lineItems.indexOf(i)
        }
    }

    fun onEditItemChange(item: lineItem) {

        Log.d("TAGTAG","edit")
//        val currentItem = requireNotNull(currentEditItem)
//        require(currentItem.id == item.id) {
            "You can only change an item with the same id as currentEditItem"
            Log.d("TAGTAG","edit1")
            var (value,id,readOnly) = item
            getIndex(item)
            Log.d("TAGTAG","currentIndex$currentEditPosition")
            val index = currentEditPosition
            Log.d("TAGTAG","value:"+value)
            if(value.isEmpty() && index!=0) {
                lineItems[currentEditPosition] = item
                changeOnlyRead(index-1 ,false)
//                                onRemoveItem(items[index])
                count--
                removeItem(lineItems[index])
                Log.d("TAGTAG", (index-1).toString()+lineItems[index-1].readonly)
            }
            else if(value.length>20) {
                if(index == count){
                    count++
                    addItem(lineItem("",false, UUID.randomUUID(),count))
                }
                Log.d("TAGTAG", value.length.toString())
                val nextItem = lineItems[currentEditPosition + 1].copy()
                nextItem.value = item.value[20].toString() +nextItem.value
//                lineItems.set(currentEditPosition+1,nextItem)
                onEditItemChange(nextItem)
                changeOnlyRead(index ,true)
            }else{
                lineItems[currentEditPosition] = item
            }
//        }


    }
}
