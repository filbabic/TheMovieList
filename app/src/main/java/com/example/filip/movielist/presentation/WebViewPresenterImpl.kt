package com.example.filip.movielist.presentation

import com.example.filip.movielist.constants.Constants
import com.example.filip.movielist.view.MovieWebView

/**
 * Created by Filip Babic @cobe
 */
class WebViewPresenterImpl : WebViewPresenter {

    private lateinit var movieWebView: MovieWebView

    override fun loadWebViewUrl() {
        movieWebView.loadMoviesUrl(Constants.WEB_VIEW_BASE_URL)
    }

    override fun cancelSubscriptions() {
        movieWebView.onRequestsCancelled()
    }

    override fun setView(view: MovieWebView) {
        movieWebView = view
    }
}