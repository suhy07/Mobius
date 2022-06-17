package edu.fzu.mobius.compose.penpal

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.base.NoShadowButton
import edu.fzu.mobius.ui.common.UnspecifiedIcon
import edu.fzu.mobius.theme.AnonEnvelope
import edu.fzu.mobius.theme.BlueText
import edu.fzu.mobius.theme.CapsuleEnvelope
import edu.fzu.mobius.theme.InviteEnvelope
import edu.fzu.mobius.ui.penpal.PenPalViewModel
import edu.fzu.mobius.ui.penpal.ReturnWritePenPalViewModel
import edu.fzu.mobius.ui.mail.WritePenPalViewModel

@ExperimentalAnimationApi
@Composable
fun PenItem(userNickname: String, abstract: String, otherNickname:String, type:Int , modifier: Modifier = Modifier) {
    //userHead:Bitmap
    //0匿名信 1笔友信 2邀请信 3时空胶囊 4鲜花

    val navController = rememberNavController()
    if(type == 4){
        Card(
            modifier = modifier
                .height(54.dp)
                .background(color = Color.Unspecified)
                .padding(start = 18.dp,end = 18.dp,bottom = 10.dp)
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
        when{
            type == 2 -> {
                visible=true
                background = InviteEnvelope
                envelopeName = "邀请信"
            }
            type == 3 ->{
                visible=true
                background = CapsuleEnvelope
                envelopeName = "时空胶囊"
            }
        }
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(174.dp)
                .padding(start = 18.dp,end = 18.dp,bottom = 10.dp)
                .background(color = Color.Unspecified)
                .clickable(
                    enabled = true,
                    role = Role.Button
                ){
//                    navController.navigate("look_write_revert_pen_pal_screen")
                }
            ,
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
                        }
                    ,
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
                PenOtherUser2(
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
fun PenOtherUser(nickname:String,id:Int,modifier: Modifier=Modifier,navController: NavController){
    val writePenPalViewModel: WritePenPalViewModel = viewModel()

    val returnWritePenPalViewModel: ReturnWritePenPalViewModel = viewModel()
    ConstraintLayout(
        modifier = modifier.width(200.dp)
    ) {
        val (name,head,rear) = createRefs()
        Text(
            text = nickname,
            color = Color.Black,
            modifier = Modifier
                .width(100.dp)
                .constrainAs(name){
                    start.linkTo(parent.start, margin = 45.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                    top.linkTo(parent.top, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin =0.dp)
                }.clickable(
                    enabled = true,
                    role = Role.Button
                ){
                    returnWritePenPalViewModel.penpalId.value=id
                    returnWritePenPalViewModel.penpalnikename.value=nickname
                    navController.navigate("revert_pen_pal_screen")

                },
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        UnspecifiedIcon(
            painter = painterResource(id = R.mipmap.head_girl),
            modifier = Modifier
                .height(45.dp)
                .padding(end = 140.dp)
                .constrainAs(head) {
                    start.linkTo(parent.start, margin = 0.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                    top.linkTo(parent.top, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin = 0.dp)
                }.clickable(
                    enabled = true,
                    role = Role.Button
                ){
                    returnWritePenPalViewModel.penpalId.value=id
                    returnWritePenPalViewModel.penpalnikename.value=nickname
                    navController.navigate("revert_pen_pal_screen")
                },
        )
        NoShadowButton(
            onClick = { /* TODO 给笔友写信笔友信息 */
                writePenPalViewModel.receiverId.value = id
                navController.navigate("write_pen_pal_screen")
            },
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .padding(start = 0.dp,top = 0.dp)
                .constrainAs(rear) {
                    end.linkTo(parent.end, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin = 0.dp)
                }
        ){
            UnspecifiedIcon(
                painter = painterResource(R.mipmap.add_icon)
            )
        }

    }
}
@Composable
fun AddPenOtherUser(
    nickname:String,
    id:Int,
    modifier: Modifier=Modifier,
    navController: NavController
){

    val returnWritePenPalViewModel: ReturnWritePenPalViewModel = viewModel()
    val PenPalViewModel: PenPalViewModel = viewModel()
    ConstraintLayout(
        modifier = modifier.width(200.dp)
    ) {
        val (name,head,rear) = createRefs()
        Text(
            text = nickname,
            color = Color.Black,
            modifier = Modifier
                .width(100.dp)
                .constrainAs(name){
                    start.linkTo(parent.start, margin = 45.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                    top.linkTo(parent.top, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin =0.dp)
                }.clickable(
                    enabled = true,
                    role = Role.Button
                ){
                    returnWritePenPalViewModel.penpalId.value=id
                    returnWritePenPalViewModel.penpalnikename.value=nickname
                    navController.navigate("revert_pen_pal_screen")
                },
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        UnspecifiedIcon(
            painter = painterResource(id = R.mipmap.head_girl),
            modifier = Modifier
                .height(45.dp)
                .padding(end = 140.dp)
                .constrainAs(head) {
                    start.linkTo(parent.start, margin = 0.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                    top.linkTo(parent.top, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin = 0.dp)
                }.clickable(
                    enabled = true,
                    role = Role.Button
                ){
                    returnWritePenPalViewModel.penpalId.value=id
                    returnWritePenPalViewModel.penpalnikename.value=nickname
                    navController.navigate("revert_pen_pal_screen")
                },
        )
        NoShadowButton(
            onClick = { /* TODO 添加好友 */
//                navController.navigate("write_pen_pal_screen")
                PenPalViewModel.strangeId.value = id
                PenPalViewModel.AddStranger(navController)
            },
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .padding(start = 0.dp,top = 0.dp)
                .constrainAs(rear) {
                    end.linkTo(parent.end, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin = 0.dp)
                }
        ){
            UnspecifiedIcon(
                painter = painterResource(R.mipmap.add_icon)
            )
        }
    }
}
@Composable
fun PenOtherUser1(nickname:String,modifier: Modifier=Modifier,navController: NavController){
    ConstraintLayout(
        modifier = modifier.width(290.dp)
    ) {
        val (name,head,rear) = createRefs()
        Text(
            text = nickname,
            color = Color.Black,
            modifier = Modifier
                .width(100.dp)
                .constrainAs(name){
                    start.linkTo(parent.start, margin = 5.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                    top.linkTo(parent.top, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin =0.dp)
                }
                .clickable(
                enabled = true,
                role = Role.Button
            ){
                        navController.navigate("revert_pen_pal_screen")
            }
            ,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        UnspecifiedIcon(
            painter = painterResource(id = R.mipmap.head_girl),
            modifier = Modifier
                .height(45.dp)
                .padding(end = 200.dp)
                .constrainAs(head) {
                    start.linkTo(parent.start, margin = 0.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                    top.linkTo(parent.top, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin = 0.dp)
                }.clickable(
                enabled = true,
                role = Role.Button
            ){
                navController.navigate("revert_pen_pal_screen")
            }
        )
        NoShadowButton(
            onClick = { /* TODO 弹出添加笔友信息 */
                navController.navigate("write_pen_pal_screen")
            },

            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .padding(start = 0.dp,top = 0.dp)
                .constrainAs(rear) {
                    end.linkTo(parent.end, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin = 0.dp)
                }
        ){
            UnspecifiedIcon(
                painter = painterResource(R.mipmap.add_icon)
            )
        }
    }
}
@Composable
fun PenOtherUser2(nickname:String,modifier: Modifier=Modifier){
    ConstraintLayout(
        modifier = modifier
    ) {
        val (name,head) = createRefs()
        Text(
            text = nickname,
            color = Color.White,
            modifier = Modifier
                .width(100.dp)
                .constrainAs(name){
                    start.linkTo(parent.start, margin = 35.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                    top.linkTo(parent.top, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin =0.dp)
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
@Composable
fun PenOtherUser3(nickname:String,modifier: Modifier=Modifier){
    ConstraintLayout(
        modifier = modifier
    ) {
        val (name,head) = createRefs()
        Text(
            text = nickname,
            color = Color.Black,
            modifier = Modifier
                .width(100.dp)
                .constrainAs(name){
                    start.linkTo(parent.start, margin = 35.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                    top.linkTo(parent.top, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin =0.dp)
                },
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        UnspecifiedIcon(
            painter = painterResource(id = R.mipmap.head_boy),
            modifier = Modifier
                .height(45.dp)
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
    val navController = rememberNavController()
    PenOtherUser1(nickname = "皇浦铁牛", navController = navController)
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewEnvelope(){
    PenItem(userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = 0)
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewInventEnvelope(){
    PenItem(userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = 2)
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewCapsuleEnvelope(){
    PenItem(userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = 3)
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewFlowerEnvelope(){
    PenItem(userNickname = "皇埔铁牛", abstract = "我想在这里告诉你个秘密..", otherNickname = "陌生人1", type = 4)
}