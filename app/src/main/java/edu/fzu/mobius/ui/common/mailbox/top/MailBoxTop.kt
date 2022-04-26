package edu.fzu.mobius.ui.common.mailbox.top

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MailBoxTop(navController: NavController,router: String,title: String = "",onClick: () -> Unit ={}) {
    TopAppBar() {
        
    }
}

@Preview
@Composable
fun PreviewMailBoxTop(){
    MailBoxTop(navController = rememberNavController(), router = "")

}