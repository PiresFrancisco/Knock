package com.piresworks.knockv2

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class TemporealActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temporeal)

        // Reference the WebView by its ID
        val webView = findViewById<WebView>(R.id.temporeal)

        // Keep navigation inside the WebView
        webView.webViewClient = WebViewClient()
        webView.settings.cacheMode = android.webkit.WebSettings.LOAD_NO_CACHE
        webView.clearCache(true)
        webView.settings.domStorageEnabled = true

        // Enable JavaScript if needed
        webView.settings.javaScriptEnabled = true

        // Load the default link
        webView.loadUrl("https://192.168.1.117:5000/")
    }
}
