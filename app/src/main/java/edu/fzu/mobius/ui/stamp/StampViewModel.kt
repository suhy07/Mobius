package edu.fzu.mobius.ui.stamp

import androidx.lifecycle.ViewModel
import edu.fzu.mobius.entity.Stamp

class StampViewModel:ViewModel() {
    val stamps = mutableListOf<Stamp>()

    init {
        stamps.add(Stamp(0))
        stamps.add(Stamp(-1))
        stamps.add(Stamp(0))
        stamps.add(Stamp(-1))
        stamps.add(Stamp(-1))
        stamps.add(Stamp(-1))
    }
}