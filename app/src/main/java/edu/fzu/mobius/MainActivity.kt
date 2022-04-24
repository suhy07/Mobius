package edu.fzu.mobius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.ui.DemoNavHost
import edu.fzu.mobius.ui.theme.MobiusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigation()
        }
    }
}

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()

    DemoNavHost(
        navController = navController,
    )
}