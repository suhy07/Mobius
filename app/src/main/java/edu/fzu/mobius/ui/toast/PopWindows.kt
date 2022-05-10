import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock

/**
 * 消息提示
 * @author markrenChina
 */
@SuppressLint("LogNotTimber")
object PopWindows {
    const val TAG = "POPWindows"

    private val queue = ConcurrentLinkedQueue<ToastMsg>()
    private val msgList = MutableList(3) { MutableStateFlow(ToastMsg("",ToastType.MESSAGE)) }
    private val position = MutableStateFlow(Position.TOP_LEFT)
    private val lock = ReentrantLock()

    enum class Position {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

    /**
     * 禁止使用log工具，会产生死锁
     */
    //@Synchronized
    fun postValue(toastMsg: ToastMsg) {
        //存入一个队列
        queue.add(toastMsg)
        try {
            lock.tryLock(10, TimeUnit.MILLISECONDS)
        } catch (e: InterruptedException) {
            return
        }
        try {
            //遍历是否有空闲显示位
            for (e in msgList) {
                if (e.value.value == "") {
                    e.value = queue.poll() ?: ToastMsg("",ToastType.MESSAGE)
                }
            }
        } finally {
            lock.unlock()
        }
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun SetPosition(pos: Position) {
        Log.i(TAG, "setPosition")
        position.value = pos
    }


    @Composable
    fun PopWin() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(msgList.size) {
                PopItem(index = it)
            }
        }
    }


    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun PopItem(index: Int) {

        val msg by msgList[index].collectAsState()

        AnimatedVisibility(
            visible = (msg.value.trim() != ""),
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
            ),
        )
        //if (msg != "")
        {
            ToastItem(type = msg.type, msg = msg.value)
        }
        LaunchedEffect(key1 = msg) {
            if (msg.value != "") {
                delay(3000)
                //移除时去查询队列，取值
                val new = queue.poll() ?: ""

                    msgList[index].value = ToastMsg("",ToastType.MESSAGE)
            }
        }
    }
    @Composable
    fun ToastItem(type:ToastType, msg: String){
        Card(
            modifier = Modifier
                .padding(10.dp)
                .height(40.dp),
            shape = RoundedCornerShape(5.dp),
            elevation = 0.dp,
            backgroundColor = when(type){
                ToastType.SUCCESS -> Color(0xFFF0F9EB).copy(0.9f)
                ToastType.WARM -> Color(0xFFFDF6EC).copy(0.9f)
                ToastType.MESSAGE -> Color(0xFFEDF2FC).copy(0.9f)
                ToastType.ERROR -> Color(0xFFFEF0F0).copy(0.9f)
            },
        ) {
            ConstraintLayout() {
                val text = createRef()
                Text(
                    text = msg,
                    color =  when(type){
                        ToastType.SUCCESS -> Color(0xFF67C23A)
                        ToastType.WARM -> Color(0xFFE6A23C)
                        ToastType.MESSAGE -> Color(0xFF909399)
                        ToastType.ERROR -> Color(0xFFF56C6C)
                    },
                    modifier = Modifier
                        .constrainAs(text){
                            start.linkTo(parent.start, margin = 25.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end, margin = 25.dp)
                        },
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 15.sp
                )
            }
        }
    }
}


enum class ToastType {
    SUCCESS,WARM,MESSAGE,ERROR
}

data class ToastMsg(
    var value: String,
    var type: ToastType
)

@Preview
@Composable
fun PreviewToast(){
    val msg = "xxx"
    PopWindows.ToastItem(msg = msg, type = ToastType.SUCCESS)
}