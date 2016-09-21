package com.example.filip.movielist.view

/**
 * Created by Filip Babic @cobe
 */
interface UsernameChangeView : BaseView {

    fun saveUsername(username: String)

    fun showSuccessMessage()

    fun showInvalidUsernameError()

    fun animateSwitchViews()
}