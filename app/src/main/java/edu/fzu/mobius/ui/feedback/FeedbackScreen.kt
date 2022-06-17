package edu.fzu.mobius.ui.feedback

import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
fun FeedbackScreen(
    navController: NavController,
) {
    val feedbackViewModel: FeedbackViewModel = viewModel()
    Scaffold(
        topBar = {
            BaseTitleTop(
                navController = navController,
                router = "mine_screen",
                title = "反馈"
            )
        },
        bottomBar = {
            ButtonBottom(
                onClick = {
                    feedbackViewModel.feedback(navController)
                },
                title = "确定"
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
                value = feedbackViewModel.feedbackValue.value,
                onValueChange = feedbackViewModel.feedbackValue.component2(),
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
fun PreviewFeedback(){
    FeedbackScreen(
        navController = rememberNavController(),
    )
}