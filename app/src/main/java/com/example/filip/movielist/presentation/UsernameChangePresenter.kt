package com.example.filip.movielist.presentation

import com.example.filip.movielist.view.UsernameChangeView

/**
 * Created by Filip Babic @cobe
 */
interface UsernameChangePresenter : Presenter<UsernameChangeView> {

    fun changeUsername(username: String?)

    fun switchViews()
}