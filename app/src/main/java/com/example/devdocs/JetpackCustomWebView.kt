package com.example.devdocs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun JetpackCustomWebView(
    url: String = "https://www.google.com/"
) {
    var isLoading by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        webView(
            url = url,
            callback = { value -> isLoading = value })
        if (isLoading) {
            LoadingAnimation()
        }
    }

}

