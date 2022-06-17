package edu.fzu.mobius.entity

import java.text.SimpleDateFormat
import java.util.*

data class Letter(
    val userNickname: String,
    val abstract: String,
    val otherNickname:String,
    val type: LetterType,
    val id: Int,
    val time: Date?
)
enum class LetterType {
    //0匿名信 1笔友信 2邀请信 3时空胶囊 4鲜花
    ANON,PEN,INVITE,TIME,FLOWER
}