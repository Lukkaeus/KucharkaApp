package com.example.kucharka.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.kucharka.R

class AlergenyActivity : AppCompatActivity() {
    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alergeny)

        webView = findViewById(R.id.webview)
        webViewSetup()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup() {
        val url: String = "https://www.obedujte.sk/alergeny"

        webView.webViewClient = WebViewClient()
        if (url.isNotEmpty()) {
            webView.loadUrl(url)
            webView.settings.javaScriptEnabled = true
        } else {
            Toast.makeText(this, "Stránka sa nenašla",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}