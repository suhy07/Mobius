package edu.fzu.mobius.ui.signin

import androidx.lifecycle.ViewModel
import edu.fzu.mobius.entity.SignInItem

class SignInViewModel: ViewModel() {
    val items = mutableListOf(SignInItem(gift = "", state = true, day = 1))
    init {
        items.clear()
        items.add(SignInItem(gift = "1点成长值", state = true, day = 1))
        items.add(SignInItem(gift = "2点成长值", state = true, day = 2))
        items.add(SignInItem(gift = "3点成长值", state = true, day = 3))
        items.add(SignInItem(gift = "4点成长值", state = true, day = 4))
        items.add(SignInItem(gift = "5点成长值", state = true, day = 5))
        items.add(SignInItem(gift = "6点成长值", state = true, day = 6))
        items.add(SignInItem(gift = "7点成长值", state = true, day = 7))
    }
    fun getBigItem(): SignInItem {
        var item = SignInItem("1点成长值", true,7)
        for (i in items) {
            if(i.day == 7)
                item = i
        }
        return item
    }

}