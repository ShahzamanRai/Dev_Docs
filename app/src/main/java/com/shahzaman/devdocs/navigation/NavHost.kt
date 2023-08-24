package com.shahzaman.devdocs.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shahzaman.devdocs.screens.DocsWebView
import com.shahzaman.devdocs.screens.HomeScreen

@Composable
fun SetupNavHost(
    navHostController: NavHostController,
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
                }
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
    }
}
