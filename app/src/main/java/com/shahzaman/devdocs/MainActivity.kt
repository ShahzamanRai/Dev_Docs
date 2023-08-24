package com.shahzaman.devdocs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.shahzaman.devdocs.navigation.SetupNavHost
import com.shahzaman.devdocs.ui.theme.DevDocsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevDocsTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                SetupNavHost(
                    navHostController = navController
                )
            }
        }
    }
}
