package edu.fzu.mobius.ui.common.nav.float

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.R

@Composable
fun NavFloatButton(onClick: () -> Unit,) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = Color.White,
        modifier = Modifier.padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.lnk),
            contentDescription = "background_image",
            modifier = Modifier
                .height(70.dp)
                .width(70.dp)
                .padding(12.dp)

        )
    }
}

@Preview
@Composable
fun PreviewFloatButton() {
    val navController = rememberNavController()
    NavFloatButton{

    }
}