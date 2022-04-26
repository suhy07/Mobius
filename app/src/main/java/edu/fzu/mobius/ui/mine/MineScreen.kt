package edu.fzu.mobius.ui.mine

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R
import edu.fzu.mobius.ui.common.nav.bottom.NavBottom
import edu.fzu.mobius.ui.theme.BlueBackground

@Composable
fun MineScreen(navController: NavController){
    Scaffold(
        //    #
        backgroundColor = BlueBackground,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Unspecified,
                modifier = Modifier.shadow(0.dp),
                elevation = 0.dp
            ) {
                ConstraintLayout() {
                    val (feedback) = createRefs()
                    TextButton(
                        onClick = { /*TODO*/ },

                    ) {
                        Text(
                            text ="反馈",
                            modifier= Modifier,
                            color = Color(0xFF5D41A7),
                        )
                    }
                }
            }
        },
        bottomBar = {
            NavBottom(navController = navController, act = 3)
        } ,
    ) {
        // Screen content
        ConstraintLayout {
            // Create references for the composables to constrain
            val (image,text1,text2) = createRefs()
        }

    }
}

@Preview
@Composable
fun PreviewMine() {
    val navController = rememberNavController()
    MineScreen(navController = navController)
}
