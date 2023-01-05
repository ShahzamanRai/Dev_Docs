package com.example.devdocs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.devdocs.ui.theme.DevDocsTheme

class WebViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevDocsTheme {
                when {
                    getId() == "android" -> JetpackCustomWebView(url = docUrls.android.url)
                    getId() == "compose" -> JetpackCustomWebView(url = docUrls.compose.url)
                    getId() == "material" -> JetpackCustomWebView(url = docUrls.material.url)
                    getId() == "tensorflow" -> JetpackCustomWebView(url = docUrls.tensorFlow.url)
                    getId() == "kotlin" -> JetpackCustomWebView(url = docUrls.kotlin.url)
                    getId() == "java" -> JetpackCustomWebView(url = docUrls.java.url)
                    getId() == "python" -> JetpackCustomWebView(url = docUrls.python.url)
                    getId() == "javascript" -> JetpackCustomWebView(url = docUrls.javaScript.url)
                    getId() == "typescript" -> JetpackCustomWebView(url = docUrls.typeScript.url)
                    getId() == "react" -> JetpackCustomWebView(url = docUrls.react.url)
                    getId() == "angular" -> JetpackCustomWebView(url = docUrls.angular.url)
                    getId() == "lua" -> JetpackCustomWebView(url = docUrls.lua.url)
                    getId() == "swift" -> JetpackCustomWebView(url = docUrls.swift.url)
                    getId() == "arch" -> JetpackCustomWebView(url = docUrls.arch.url)

                }
            }
        }
    }

    private fun getId(): String? {
        return intent.extras?.getString("id")

    }
}



