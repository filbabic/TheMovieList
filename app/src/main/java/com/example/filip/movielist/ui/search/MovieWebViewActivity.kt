package com.example.filip.movielist.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.filip.movielist.App
import com.example.filip.movielist.R
import com.example.filip.movielist.presentation.WebViewPresenter
import com.example.filip.movielist.ui.base.BaseActivity
import com.example.filip.movielist.view.MovieWebView
import javax.inject.Inject

/**
 * Created by Filip Babic @cobe
 */
class MovieWebViewActivity : BaseActivity(), MovieWebView {

    private lateinit var webView: WebView

    @Inject lateinit var webViewPresenter: WebViewPresenter

    companion object {

        fun getLaunchIntent(from: Context): Intent {
            return Intent(from, MovieWebViewActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        App.component.inject(this)
        initUI()
        prepareData()
    }

    override fun initUI() {
        webView = findViewById(R.id.search_activity_web_view) as WebView
        webView.setWebViewClient(WebViewClient())
    }

    override fun prepareData() {
        webViewPresenter.setView(this)
        webViewPresenter.loadWebViewUrl()
    }

    override fun loadMoviesUrl(movieUrl: String) {
        webView.loadUrl(movieUrl)
    }

    override fun onRequestsCancelled() {
    }
}