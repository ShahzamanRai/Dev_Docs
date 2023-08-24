package com.shahzaman.devdocs.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shahzaman.devdocs.components.LoadingAnimation
import com.shahzaman.devdocs.components.WebView

@Composable
fun DocsWebView(
    url: String
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            WebView(
                url = url,
                callback = { value -> isLoading = value })
            if (isLoading) {
                LoadingAnimation()
            }
        }
    }
}

