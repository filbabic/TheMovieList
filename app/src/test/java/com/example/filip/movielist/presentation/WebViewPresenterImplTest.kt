package com.example.filip.movielist.presentation

import com.example.filip.movielist.view.MovieWebView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by Filip Babic @cobe
 */
@RunWith(MockitoJUnitRunner::class)
class WebViewPresenterImplTest {

    private lateinit var presenter: WebViewPresenterImpl

    @Mock lateinit var webView: MovieWebView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = WebViewPresenterImpl()
        presenter.setView(webView)
    }

    @Test
    fun testLoadWebView() {
        presenter.loadWebViewUrl()

        verify(webView).loadMoviesUrl(Matchers.anyString())

        verifyNoMoreInteractions(webView)
    }

    @Test
    fun testCancelSubscriptions() {
        presenter.cancelSubscriptions()

        verify(webView).onRequestsCancelled()

        verifyNoMoreInteractions(webView)
    }
}