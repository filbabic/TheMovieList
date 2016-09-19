package com.example.filip.movielist.common.extensions

import com.example.filip.movielist.presentation.Presenter
import com.example.filip.movielist.view.BaseView
import rx.SingleSubscriber

/**
 * Created by Filip Babic @cobe
 */


fun <T> Presenter<out BaseView>.cancelRequests(vararg subscribers: SingleSubscriber<T>?) {
    subscribers.map { it?.cancelRequest() }

    onRequestsCancelled()
}