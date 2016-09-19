package com.example.filip.movielist.presentation

/**
 * Created by Filip Babic @cobe
 */
interface Presenter<T> {

    fun setView(view: T)

    fun cancelSubscriptions()

    fun onRequestsCancelled()
}