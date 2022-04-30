package edu.fzu.mobius.ui.write

import java.util.*

data class lineItem(
    var value: String,
    // since the user may generate identical tasks, give them each a unique ID
    val id: UUID = UUID.randomUUID()
)