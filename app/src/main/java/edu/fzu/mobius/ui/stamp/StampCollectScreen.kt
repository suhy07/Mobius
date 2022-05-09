package edu.fzu.mobius.ui.stamp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.entity.Stamp
import edu.fzu.mobius.theme.BlueBackground
import edu.fzu.mobius.ui.common.UnspecifiedIcon

@ExperimentalMaterialApi
@Composable
fun StampCollectScreen(
    navController: NavController,
    stamps: List<Stamp>
) {
    Scaffold(
        backgroundColor = BlueBackground,
        topBar = {
            BaseTitleTop(
                navController = navController,
                title = "邮票收集",
                router = "mine_screen"
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 30.dp,end = 30.dp)
        ){
            items(stamps){
                StampItemScreen(type = it.type)
            }
        }
    }
}

@Composable
fun StampItemScreen(
    type: Int
){
    Card(
        modifier = Modifier
            .height(192.dp)
            .width(400.dp)
            .padding(all = 5.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        UnspecifiedIcon(
            painter = when(type){
                -1 -> painterResource(id = R.drawable.stamp_empty)
                0 -> painterResource(id = R.drawable.stamp0)
                else -> painterResource(id = R.drawable.stamp_empty)
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun PreviewStamp0(){
    StampItemScreen(type = 0)
}

@Preview
@Composable
fun PreviewStamp1(){
    StampItemScreen(type = -1)
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewStampCollect(){
    val stampViewModel:StampViewModel = viewModel()
    StampCollectScreen(
        navController = rememberNavController(),
        stamps = stampViewModel.stamps
    )
}