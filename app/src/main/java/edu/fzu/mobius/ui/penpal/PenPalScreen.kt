package edu.fzu.mobius.ui.penpal

import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.ui.common.NoShadowTopAppBar
import edu.fzu.mobius.ui.common.nav.bottom.NavBottom
import edu.fzu.mobius.ui.theme.BlueBackground

@Composable
fun PenPalScreen(navController: NavController){
    Scaffold(
        //    #
        backgroundColor = BlueBackground,
        topBar = {
            NoShadowTopAppBar() {
                /*Top Context*/
            }
        },
        bottomBar = {
            NavBottom(navController = navController, act = 1)
        } ,
    ) {
        // Screen content
        ConstraintLayout {

        }
    }
}

@Preview
@Composable
fun PreviewPenPal() {
    val navController = rememberNavController()
    PenPalScreen(navController = navController)
}