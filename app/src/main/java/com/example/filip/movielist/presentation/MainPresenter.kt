package com.example.filip.movielist.presentation

import com.example.filip.movielist.view.MainView

/**
 * Created by Filip Babic @cobe
 */
interface MainPresenter : Presenter<MainView> {

    fun handleNavigationItemClick(itemId: Int)

    fun handleHomeButtonClick()

    fun handleConnectionStatus()

    fun handleSearchButtonClick()
}