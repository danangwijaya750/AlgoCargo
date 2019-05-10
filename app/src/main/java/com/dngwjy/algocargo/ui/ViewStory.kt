package com.dngwjy.algocargo.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dngwjy.algocargo.R
import kotlinx.android.synthetic.main.activity_view_story.*

class ViewStory : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_story)
        supportActionBar?.hide()
        val url=intent.getStringExtra("url")
        webView.settings.javaScriptEnabled=true
        webView.settings.domStorageEnabled=true
        webView.settings.cacheMode=WebSettings.LOAD_NO_CACHE
        webView.webChromeClient= WebChromeClient()
        webView.clearFormData()
        webView.clearCache(true)
       webView.loadUrl(url)
    }

     class loadStory():WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            if (view != null) {
                view.loadUrl(url)
            }
            return true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}
