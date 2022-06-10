package edu.fzu.mobius.entity

data class Letter (
    val userNickname: String,
    val abstract: String,
    val otherNickname:String,
    val type: LetterType,
    val id: Int
)
enum class LetterType {
    //0匿名信 1笔友信 2邀请信 3时空胶囊 4鲜花
    ANON,PEN,INVITE,TIME,FLOWER
}