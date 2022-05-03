package edu.fzu.mobius.compose

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.fzu.mobius.theme.BlueText

@Composable
fun CountdownButton(
    tips: String,
    tipsAft: String,
    time: Int = 60 ,
    modifier: Modifier = Modifier,
    onClick: ()->Unit = {},
) {
    var count = remember { mutableStateOf(time) }
    val state = remember { mutableStateOf(true) }
    val countDownThread = Thread {
        run {
            state.value = false
            while (count.value > 0) {
                count.value--
                Thread.sleep(1000)
            }
            count.value = time
            state.value = true
        }
    }

    TextButton(
        enabled = state.value,
        onClick = {
            onClick()
            countDownThread.start()
        },
        modifier = modifier
    ) {
        Text(
            text = when(state.value){
               true-> tips
               false -> "$tipsAft(${count.value})"
            },
            modifier = Modifier
                .fillMaxSize(),
            textAlign = TextAlign.Center,
            color = when(state.value){
                true-> BlueText
                false -> Color.Gray
            },
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}