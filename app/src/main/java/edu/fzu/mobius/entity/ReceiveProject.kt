package edu.fzu.mobius.entity



data class ReceiveProject(
    val id: Int,
    val receiverId: Int,
    val contentbrief: String,
    val title: String,
    val receiverNickname: String,
    val nickName: String,
)