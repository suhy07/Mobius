package edu.fzu.mobius.ui.capsule

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
fun CapsuleScreen(navController: NavController){
    Scaffold(
        //    #
        backgroundColor = BlueBackground,
        topBar = {
            NoShadowTopAppBar(
            ) {
                /*Top Context*/
            }
        },
        bottomBar = {
            NavBottom(navController = navController, act = 2)
        } ,
    ) {
        // Screen content
        ConstraintLayout {

        }
    }
}

@Preview
@Composable
fun PreviewCapsule() {
    val navController = rememberNavController()
    CapsuleScreen(navController = navController)
}