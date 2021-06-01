package com.example.kucharka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class AlergenyActivity : AppCompatActivity() {
    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alergeny)


        webView = findViewById(R.id.webview)
        webView.loadUrl("https://www.obedujte.sk/alergeny")

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled

    }

    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        } else{
        super.onBackPressed()
    }
    }
}