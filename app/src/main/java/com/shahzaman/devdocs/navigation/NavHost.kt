package com.shahzaman.devdocs.navigation

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shahzaman.devdocs.SettingsModel
import com.shahzaman.devdocs.components.PrivacyPolicyScreen
import com.shahzaman.devdocs.screens.DocsWebView
import com.shahzaman.devdocs.screens.HomeScreen
import com.shahzaman.devdocs.screens.PreferenceScreen

@Composable
fun SetupNavHost(
    navHostController: NavHostController,
    settingsModel: SettingsModel,
    context: Context
) {
    NavHost(
        navController = navHostController,
        startDestination = "home"
    ) {
        composable(
            route = "home"
        ) {
            HomeScreen(
                onNavigateToWeb = { url ->
                    val encodedUrl = Uri.encode(url)
                    navHostController.navigate("web_view/$encodedUrl")
                },
                navHostController = navHostController
            )
        }

        composable(
            route = "web_view/{url}",
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            DocsWebView(
                url = url
            )
        }

        composable(
            route = "setting"
        ) {
            PreferenceScreen(
                settingsModel = settingsModel,
                navHostController = navHostController,
                context = context
            )
        }
        composable(
            route = "policy"
        ) {
            PrivacyPolicyScreen(
                context = context
            )
        }
    }
}
