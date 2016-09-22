package com.example.filip.movielist.ui.main

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.TextView
import com.example.filip.movielist.App
import com.example.filip.movielist.R
import com.example.filip.movielist.common.extensions.toast
import com.example.filip.movielist.common.utils.NetworkUtils
import com.example.filip.movielist.constants.Constants
import com.example.filip.movielist.data.preference.PreferenceHelper
import com.example.filip.movielist.presentation.MainPresenter
import com.example.filip.movielist.ui.base.BaseActivity
import com.example.filip.movielist.ui.cachedmovies.CachedMoviesActivity
import com.example.filip.movielist.ui.favorite.FavoriteMoviesActivity
import com.example.filip.movielist.ui.main.adapter.MoviePageAdapter
import com.example.filip.movielist.ui.movie.view.MoviesListFragment
import com.example.filip.movielist.ui.search.MovieWebViewActivity
import com.example.filip.movielist.ui.username.UsernameChangeActivity
import com.example.filip.movielist.view.MainView
import javax.inject.Inject

/**
 * Created by Filip Babic @cobe
 */
class MoviesActivity : BaseActivity(), MainView {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var searchButton: FloatingActionButton

    private lateinit var moviePager: ViewPager
    private lateinit var movieAdapter: MoviePageAdapter

    @Inject lateinit var presenter: MainPresenter

    @Inject lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.component.inject(this)
        prepareData()
        initUI()
    }

    override fun onResume() {
        super.onResume()
        setUsername()
    }

    override fun prepareData() {
        presenter.setView(this)
        movieAdapter = MoviePageAdapter(supportFragmentManager)

        movieAdapter.addItem(MoviesListFragment.newInstance(Constants.MOVIE_TYPE_TOP_RATED), Constants.MOVIE_TYPE_TOP_RATED)
        movieAdapter.addItem(MoviesListFragment.newInstance(Constants.MOVIE_TYPE_POPULAR), Constants.MOVIE_TYPE_POPULAR)
        movieAdapter.addItem(MoviesListFragment.newInstance(Constants.MOVIE_TYPE_UPCOMING), Constants.MOVIE_TYPE_UPCOMING)

        presenter.handleConnectionStatus(NetworkUtils.isInternetAvailable())
    }

    override fun initUI() {
        initToolbar()
        initPager()
        initSearchButton()
        initDrawer()
    }

    private fun initDrawer() {
        drawerLayout = findViewById(R.id.movies_drawer_layout) as DrawerLayout
        navigationView = findViewById(R.id.movies_navigation_view) as NavigationView

        setUsername()

        navigationView.setNavigationItemSelectedListener { handleMenuItemClick(it.itemId) }
    }

    private fun setUsername() {
        if (navigationView.headerCount != 0) {
            val username = navigationView.getHeaderView(0).findViewById(R.id.navigation_view_username) as TextView

            username.text = preferenceHelper.getUsername()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        presenter.handleHomeButtonClick()
        return true
    }

    private fun handleMenuItemClick(itemId: Int): Boolean {
        presenter.handleNavigationItemClick(itemId = itemId)
        return false
    }

    private fun initSearchButton() {
        searchButton = findViewById(R.id.movie_search_button) as FloatingActionButton
        searchButton.setOnClickListener { startMovieSearch() }
    }

    private fun startMovieSearch() {
        presenter.handleSearchButtonClick()
    }

    private fun initPager() {
        moviePager = findViewById(R.id.main_activity_view_pager) as ViewPager
        moviePager.offscreenPageLimit = 3
        moviePager.adapter = movieAdapter
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)

        val supportActionBar = supportActionBar
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun closeNavigationDrawer() {
        drawerLayout.closeDrawers()
    }

    override fun openNavigationDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun showNoConnectionError() {
        toast(getString(R.string.no_internet_connection_error))
    }

    override fun startChangeUsernameActivity() {
        startActivity(UsernameChangeActivity.getLaunchIntent(from = this))
    }

    override fun startDatabaseActivity() {
        startActivity(CachedMoviesActivity.getLaunchIntent(from = this))
    }

    override fun startFavoriteMoviesActivity() {
        startActivity(FavoriteMoviesActivity.getLaunchIntent(from = this))
    }

    override fun startSearchActivity() {
        startActivity(MovieWebViewActivity.getLaunchIntent(from = this))
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down)
    }

    override fun onRequestsCancelled() {
    }
}