package com.example.devdocs

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStreamReader

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun webView(
    url: String,
    callback: (isLoading: Boolean) -> Unit
) {
    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null
    val context = LocalContext.current

    //.................................................
    // Compose WebView Part 9 | Removes or Stop Ad in web
    val adServers = StringBuilder()
    var line: String? = ""
    val inputStream = context.resources.openRawResource(R.raw.adblockserverlist)
    val br = BufferedReader(InputStreamReader(inputStream))
    try {
        while (br.readLine().also { line = it } != null) {
            adServers.append(line)
            adServers.append("\n")
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }


    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.userAgentString
                settings.userAgentString = System.getProperty("http.agent")
                settings.domStorageEnabled
                settings.setSupportZoom(true)
                settings.builtInZoomControls = true
                settings.displayZoomControls = false
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                        backEnabled = view.canGoBack()
                        callback(true)
                    }

                    override fun onReceivedError(
                        view: WebView?,
                        request: WebResourceRequest?,
                        error: WebResourceError?
                    ) {
                        Toast.makeText(context, "No intenet connection", Toast.LENGTH_SHORT).show()
                        super.onReceivedError(view, request, error)
                    }

                    override fun shouldInterceptRequest(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): WebResourceResponse? {
                        val empty = ByteArrayInputStream("".toByteArray())
                        val kk5 = adServers.toString()
                        if (request != null) {
                            if (kk5.contains(":::::" + request.url.host))
                                return WebResourceResponse("text/plain", "utf-8", empty)
                        }
                        return super.shouldInterceptRequest(view, request)
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        callback(false)
                    }
                }
                webChromeClient = WebChromeClient()
                loadUrl(url)
                webView = this
            }

        }, update = {
            webView = it
        })
    BackHandler(enabled = backEnabled) {
        webView?.goBack()
    }
}
