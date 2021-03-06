package edu.fzu.mobius.ui.draft

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.compose.BaseTitleTop
import edu.fzu.mobius.compose.ButtonBottom
import edu.fzu.mobius.compose.EmptyTextField
import edu.fzu.mobius.entity.Draft
import edu.fzu.mobius.theme.PrimaryVariant

@Composable
fun DraftEditScreen(
    navController: NavController,
    id: Int
) {
    val draftViewModel: DraftViewModel = viewModel()
    draftViewModel.getDraft(id)
    Scaffold(
        topBar = {
            BaseTitleTop(
                navController = navController,
                router = "drafts_screen",
                title = "编辑"
            )
        },
        bottomBar = {
            ButtonBottom(
                onClick = {
                    draftViewModel.saveDraft(id, draftViewModel.content.value, navController)
                },
                title = "提交"
            )
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, start = 25.dp, end = 25.dp, bottom = 200.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = 5.dp
        ) {
            EmptyTextField(
                value = draftViewModel.content.value,
                onValueChange = draftViewModel.content.component2(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Unspecified,
                    focusedIndicatorColor = Color.Unspecified,
                    unfocusedIndicatorColor = Color.Unspecified,
                    cursorColor = Color.Blue
                ),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    textDecoration = TextDecoration.None,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Left,
                ),
            )
        }

    }
}

@Preview
@Composable
fun PreviewDraftEdit(){
    DraftEditScreen(
        navController = rememberNavController(),
        id = 0
    )
}