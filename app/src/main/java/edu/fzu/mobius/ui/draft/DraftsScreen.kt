package edu.fzu.mobius.ui.draft

import androidx.compose.foundation.clickable
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.entity.Draft
import edu.fzu.mobius.navigation.navigateAndArgument

@ExperimentalMaterialApi
@Composable
fun DraftsScreen(
    navController: NavController,
) {
    val draftViewModel: DraftViewModel = viewModel()
    draftViewModel.getDrafts()
    Scaffold(
        topBar = {
            BaseTitleTop(
                navController = navController,
                router = "mine_screen",
                title = "草稿箱"
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 30.dp,end = 30.dp)
        ){
            items(draftViewModel.drafts){
                DraftItemScreen(
                    navController = navController,
                    id = it.id,
                    value = it.value
                )
            }
        }
    }
}


@Composable
fun DraftItemScreen(
    navController: NavController,
    id: Int,
    value: String
){
    Card(
        modifier = Modifier
            .height(165.dp)
            .width(400.dp)
            .padding(all = 5.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp
    ) {
        Text(
            text = value,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.dp)
                .clickable {
                    val args = listOf(Pair("id",id))
                    navController.navigateAndArgument("draft_edit_screen/{id}", args)
                },
        )
    }
}

@Preview
@Composable
fun PreviewDraftItem(){
    DraftItemScreen(
        navController = rememberNavController(),
        id = 0,
        value = "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_" +
                "too_long_too_long_too_long_too_long_too_long_too_long_too_long_too_long_"
    )
}


@Preview
@ExperimentalMaterialApi
@Composable
fun PreviewDrafts(){
    DraftsScreen(
        navController = rememberNavController(),
    )
}