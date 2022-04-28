package edu.fzu.mobius.ui.mail

import java.util.*

data class lineItem(
    var value: String,
    // since the user may generate identical tasks, give them each a unique ID
    var readonly: Boolean = false,
    val id: UUID = UUID.randomUUID(),
    val index: Int
)