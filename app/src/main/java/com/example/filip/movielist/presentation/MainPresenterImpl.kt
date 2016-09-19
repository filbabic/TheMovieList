package com.example.filip.movielist.presentation

import com.example.filip.movielist.R
import com.example.filip.movielist.common.utils.NetworkUtils
import com.example.filip.movielist.view.MainView

/**
 * Created by Filip Babic @cobe
 */
class MainPresenterImpl : MainPresenter {

    private lateinit var mainView: MainView

    override fun handleConnectionStatus() {
        if (!NetworkUtils.isInternetAvailable()) {
            mainView.showNoConnectionError()
        }
    }

    override fun handleHomeButtonClick() {
        mainView.openNavigationDrawer()
    }

    override fun handleNavigationItemClick(itemId: Int) {
        when (itemId) {
            R.id.action_favorite -> mainView.startFavoriteMoviesActivity()

            R.id.action_database -> mainView.startDatabaseActivity()

            R.id.action_username -> mainView.startChangeUsernameActivity()

            else -> mainView.closeNavigationDrawer()
        }
    }

    override fun handleSearchButtonClick() {
        mainView.startSearchActivity()
    }

    override fun cancelSubscriptions() {
    }

    override fun onRequestsCancelled() {
    }

    override fun setView(view: MainView) {
        mainView = view
    }
}