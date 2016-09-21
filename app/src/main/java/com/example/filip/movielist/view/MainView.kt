package com.example.filip.movielist.view

/**
 * Created by Filip Babic @cobe
 */
interface MainView : BaseView {

    fun startDatabaseActivity()

    fun startChangeUsernameActivity()

    fun startFavoriteMoviesActivity()

    fun startSearchActivity()

    fun showNoConnectionError()

    fun openNavigationDrawer()

    fun closeNavigationDrawer()
}