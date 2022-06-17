package edu.fzu.mobius.compose.mailbox.item

import ToastMsg
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.entity.LetterType
import edu.fzu.mobius.navigation.navigateAndArgument
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.theme.AnonEnvelope
import edu.fzu.mobius.theme.BlueText
import edu.fzu.mobius.theme.CapsuleEnvelope
import edu.fzu.mobius.theme.InviteEnvelope

@ExperimentalAnimationApi
@Composable
fun Envelope(
    navController:NavController,
    id: Int,
    userNickname: String,
    abstract: String,
    otherNickname: String,
    type: LetterType,
    modifier: Modifier = Modifier,
    clickable: Boolean = true,
) {
    //userHead:Bitmap
    //0匿名信 1笔友信 2邀请信 3时空胶囊 4鲜花
    if(type == LetterType.FLOWER){
        Card(
            modifier = modifier
                .height(54.dp)
                .background(color = Color.Unspecified)
                .padding(start = 18.dp, end = 18.dp, bottom = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            UnspecifiedIcon(
                painter = painterResource(id = R.mipmap.flow_icon),
                contentDescription="background_image",
                modifier = Modifier
                    .height(40.dp)
                    .width(60.dp)
                    .padding(top = 5.dp, end = 310.dp, bottom = 5.dp)
            )
            Text(
                text = "收到了一束鲜花",
                modifier = Modifier
                    .padding(top = 14.dp,start = 60.dp),
                fontSize = 16.sp,
                color = BlueText
            )
        }
    }else{
        var background = AnonEnvelope
        var visible = false
        var envelopeName = ""
        when(type){
            LetterType.INVITE -> {
                visible=true
                background = InviteEnvelope
                envelopeName = "邀请信"
            }
            LetterType.TIME ->{
                visible=true
                background = CapsuleEnvelope
                envelopeName = "时空胶囊"
            }
        }
        Card(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    enabled = clickable,
                    onClick = {
                        when (type) {
                            LetterType.ANON -> {
                                val args = listOf(Pair("id", id))
                                navController.navigateAndArgument(
                                    route = "read_anon_mail_screen/{id}",
                                    args = args
                                )
                            }
                            LetterType.INVITE ->{
                                val args = listOf(Pair("id", id), Pair("nickname",otherNickname))
                                navController.navigateAndArgument(
                                    route = "invite_screen/{id}/{nickname}",
                                    args = args
                                )
                            }
                        }

                    }
                )
                .height(174.dp)
                .padding(start = 18.dp, end = 18.dp, bottom = 10.dp)
                .background(color = Color.Unspecified),
            backgroundColor = background,
            shape = RoundedCornerShape(10.dp)
        ) {
            ConstraintLayout {
                val(user,abs,tips,other) = createRefs()
                Text(
                    text = "To:$userNickname",
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(user){
                            start.linkTo(parent.start, margin = 0.dp)
                            end.linkTo(parent.end, margin = 180.dp)
                            top.linkTo(parent.top, margin = 0.dp)
                            bottom.linkTo(parent.bottom, margin = 100.dp)
                        },
                    fontSize = 16.sp
                )
                Text(
                    text = abstract,
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(abs){
                            start.linkTo(parent.start, margin = 0.dp)
                            end.linkTo(parent.end, margin = 40.dp)
                            top.linkTo(parent.top, margin = 0.dp)
                            bottom.linkTo(parent.bottom, margin = 20.dp)
                        },
                    fontSize = 16.sp
                )
                AnimatedVisibility(
                    visible = visible,
                    modifier = Modifier
                    .constrainAs(tips){
                        start.linkTo(parent.start, margin = 0.dp)
                        top.linkTo(parent.top, margin = 90.dp)
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                    },) {
                    Card(
                        shape = RoundedCornerShape(topEnd = 10.dp,bottomEnd = 10.dp),
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .background(Color.Unspecified)
                            .height(35.dp)
                            .width(80.dp)
                    ) {
                        Text(
                            text = envelopeName,
                            modifier = Modifier
                                .padding(top = 10.dp,start = 10.dp),
                            fontSize = 16.sp,
                            color = BlueText
                        )
                    }
                }
                OtherUser(
                    nickname = otherNickname,
                    modifier = Modifier
                        .constrainAs(other){
                            start.linkTo(parent.start, margin = 260.dp)
                            end.linkTo(parent.end, margin = 0.dp)
                            top.linkTo(parent.top, margin = 120.dp)
                            bottom.linkTo(parent.bottom, margin = 10.dp)
                        }
                )
            }
        }
    }
}

@Composable
fun OtherUser(nickname:String,modifier: Modifier=Modifier){
    ConstraintLayout(
        modifier = modifier
    ) {
        val (name,head) = createRefs()
        Text(
            text = nickname,
            color = Color.White,
            modifier = Modifier
                .width(100.dp)
                .constrainAs(name) {
                    start.linkTo(parent.start, margin = 35.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                    top.linkTo(parent.top, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin = 0.dp)
                },
            fontSize = 16.sp,
            textAlign = TextAlign.Left
        )
        UnspecifiedIcon(
            painter = painterResource(id = R.mipmap.head_girl),
            modifier = Modifier
                .height(25.dp)
                .padding(end = 110.dp)
                .constrainAs(head) {
                    start.linkTo(parent.start, margin = 0.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                    top.linkTo(parent.top, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
        )
    }
}

@Preview
@Composable
fun PreviewOtherUser(){
    OtherUser(nickname = "皇浦铁牛")
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewEnvelope(){
    Envelope(navController = rememberNavController(), id = 0, userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = LetterType.ANON)
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewInventEnvelope(){
    Envelope(navController = rememberNavController(), id = 0, userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = LetterType.PEN)
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewCapsuleEnvelope(){
    Envelope(navController = rememberNavController(), id = 0, userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = LetterType.INVITE)
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewFlowerEnvelope(){
    Envelope(navController = rememberNavController(), id = 0, userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = LetterType.FLOWER)
}