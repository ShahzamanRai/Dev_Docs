package com.shahzaman.devdocs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.shahzaman.devdocs.navigation.SetupNavHost
import com.shahzaman.devdocs.ui.theme.DevDocsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsModel: SettingsModel = viewModel()
            installSplashScreen()
            DevDocsTheme(
                dynamicColor = true,
                darkTheme = when (settingsModel.themeMode) {
                    "system" -> isSystemInDarkTheme()
                    else -> settingsModel.themeMode == "dark"
                }
            ) {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                SetupNavHost(
                    navHostController = navController,
                    settingsModel = settingsModel,
                    context = baseContext
                )
            }
        }
    }
}

