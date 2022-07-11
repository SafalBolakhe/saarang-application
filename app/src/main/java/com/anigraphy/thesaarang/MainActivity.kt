package com.anigraphy.thesaarang

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient
import android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


var url = "https://thesaarang.com/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mWebView: WebView = findViewById(R.id.webView)
        mWebView.loadUrl(url)
        mWebView.webViewClient = WebViewClient()

        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.setGeolocationEnabled(true);
        webSettings.databaseEnabled = true;
        webSettings.domStorageEnabled = true;
        webSettings.cacheMode = LOAD_CACHE_ELSE_NETWORK;


        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onGeolocationPermissionsShowPrompt(
                origin: String,
                callback: GeolocationPermissions.Callback
            ) {
                // callback.invoke(String origin, boolean allow, boolean remember);
                callback.invoke(origin, true, false)
            }
        }

        mWebView.canGoBack()
        mWebView.setOnKeyListener(View.OnKeyListener {v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK

                &&event.action == MotionEvent.ACTION_UP
                &&mWebView.canGoBack()){
                mWebView.goBack()
                return@OnKeyListener true
            }
            false
        })
        configureQrButton();
    }

    private fun configureQrButton() {
        val button = findViewById<Button>(R.id.qrbutton)
        button.setOnClickListener(){
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent);
//            finish();
    }
    }
}