package edu.fzu.mobius.ui.mail

import java.util.*

data class LineItem(
    var value: String,
    // since the user may generate identical tasks, give them each a unique ID
    val id: UUID = UUID.randomUUID()
)