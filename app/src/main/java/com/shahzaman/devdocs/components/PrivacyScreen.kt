package com.shahzaman.devdocs.components

import android.content.Context
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun PrivacyPolicyScreen(context: Context) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val htmlContent = context.assets.open("dev_docs_privacy_policy.html")
            .bufferedReader()
            .use { it.readText() }


        AndroidView(
            factory = { WebView(context) },
            modifier = Modifier.fillMaxSize()
        ) { webView ->
            val settings: WebSettings = webView.settings
            settings.javaScriptEnabled = true
            webView.canGoBack()
            webView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
        }
    }
}