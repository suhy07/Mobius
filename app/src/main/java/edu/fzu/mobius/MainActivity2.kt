package edu.fzu.mobius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import edu.fzu.mobius.ui.mail.WriteMailScreen
import edu.fzu.mobius.ui.mail.WriteMailViewModel
import edu.fzu.mobius.ui.mail.lineItem
import edu.fzu.mobius.ui.theme.MobiusTheme
import java.util.*

class MainActivity2 : ComponentActivity() {
    val writeMailViewModel by viewModels<WriteMailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobiusTheme {
                // A surface container using the 'background' color from the theme
               MainActivity2Screen(viewModel =writeMailViewModel )
            }
        }
    }
}


@Composable
fun MainActivity2Screen(viewModel: WriteMailViewModel){
    WriteMailScreen(navController = rememberNavController()
        , items = viewModel.lineItems
        , onAddItem = viewModel::addItem
        , onRemoveItem = viewModel::removeItem
        , changeOnlyRead = viewModel::changeOnlyRead
        , onEditItemChange = viewModel::onEditItemChange
        , onItemClicked = viewModel::onEditItemSelected
    )
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
   MainActivity2Screen(viewModel = viewModel())
}