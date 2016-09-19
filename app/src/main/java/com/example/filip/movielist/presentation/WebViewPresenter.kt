package com.example.filip.movielist.presentation

import com.example.filip.movielist.view.MovieWebView

/**
 * Created by Filip Babic @cobe
 */
interface WebViewPresenter : Presenter<MovieWebView> {

    fun loadWebViewUrl()
}