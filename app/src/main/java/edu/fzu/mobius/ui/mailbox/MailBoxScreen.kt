package edu.fzu.mobius.ui.mailbox

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Preview
@Composable
fun MailBoxScreen(navController: NavController){
    Scaffold(
        bottomBar = {
            BottomAppBar { 
                Button(onClick = { /*TODO*/ },Modifier.testTag("click")) {
                    
                }
            }
        } ,
        floatingActionButton = {
            FloatingActionButton(onClick = { /* ... */ }) {
                /* FAB content */
            }
        }
    ) {
        // Screen content
    }
}

