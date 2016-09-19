package com.example.filip.movielist.ui.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.filip.movielist.App
import com.example.filip.movielist.R
import com.example.filip.movielist.common.extensions.toast
import com.example.filip.movielist.listeners.ItemListener
import com.example.filip.movielist.model.MovieListModel
import com.example.filip.movielist.presentation.FavoriteMoviesPresenter
import com.example.filip.movielist.ui.base.BaseActivity
import com.example.filip.movielist.ui.movie.adapter.MoviesAdapter
import com.example.filip.movielist.ui.movie.view.MovieDetailsActivity
import com.example.filip.movielist.view.FavoriteMoviesView
import javax.inject.Inject

/**
 * Created by Filip Babic @cobe
 */
class FavoriteMoviesActivity : BaseActivity(), FavoriteMoviesView, ItemListener {

    private lateinit var toolbar: Toolbar
    private lateinit var favoriteMovies: RecyclerView

    private lateinit var mAdapter: MoviesAdapter

    @Inject lateinit var favoriteMoviesPresenter: FavoriteMoviesPresenter

    companion object {

        fun getLaunchIntent(from: Context): Intent {
            return Intent(from, FavoriteMoviesActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movies)
        App.component.inject(this)
        initUI()
        prepareData()
    }

    override fun initUI() {
        toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.title = getString(R.string.activity_favorite_movies_title)

        favoriteMovies = findViewById(R.id.favorite_movies) as RecyclerView
        favoriteMovies.setHasFixedSize(true)
        favoriteMovies.layoutManager = LinearLayoutManager(this)
        favoriteMovies.itemAnimator = DefaultItemAnimator()
    }

    override fun prepareData() {
        favoriteMoviesPresenter.setView(this)

        mAdapter = MoviesAdapter(this)
        favoriteMovies.adapter = mAdapter

        favoriteMoviesPresenter.getFavoriteMovies()
    }

    override fun setAdapterItems(items: MutableList<MovieListModel>?) {
        mAdapter.setItems(items, true)
    }

    override fun showNoMoviesError() {
        toast(message = getString(R.string.database_movie_error))
    }

    override fun onRequestsCancelled() {
    }

    override fun onLastItemReached() {
        //ok nothing
    }

    override fun onItemClick(movieID: Int, viewToTransit: View) {
        val intent: Intent = MovieDetailsActivity.getLaunchIntent(this, movieID)
        val activityCompatOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, viewToTransit, getString(R.string.image_view_transition))
        startActivity(intent, activityCompatOptions.toBundle())
    }
}