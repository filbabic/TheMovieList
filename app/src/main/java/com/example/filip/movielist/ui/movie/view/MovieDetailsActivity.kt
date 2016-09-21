package com.example.filip.movielist.ui.movie.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.example.filip.movielist.App
import com.example.filip.movielist.R
import com.example.filip.movielist.common.extensions.loadImage
import com.example.filip.movielist.common.extensions.toast
import com.example.filip.movielist.common.utils.NetworkUtils
import com.example.filip.movielist.constants.Constants
import com.example.filip.movielist.presentation.MovieDetailsPresenter
import com.example.filip.movielist.ui.base.BaseActivity
import com.example.filip.movielist.view.MovieDetailsView
import java.util.*
import javax.inject.Inject

/**
 * Created by Filip Babic @cobe
 */
class MovieDetailsActivity : BaseActivity(), MovieDetailsView {

    private lateinit var moviePoster: ImageView
    private lateinit var movieDetails: TextView
    private lateinit var movieRelease: TextView
    private lateinit var movieRevenue: TextView
    private lateinit var movieGenres: TextView
    private lateinit var movieRuntime: TextView
    private lateinit var movieGrade: TextView
    private lateinit var movieStatus: TextView

    private lateinit var toolbar: Toolbar
    private lateinit var favoriteButton: FloatingActionButton
    private lateinit var scrollView: NestedScrollView

    @Inject lateinit var presenter: MovieDetailsPresenter

    companion object {

        fun getLaunchIntent(from: Context, movieId: Int): Intent {
            val intent = Intent(from, MovieDetailsActivity::class.java)
            intent.putExtra(Constants.MOVIE_ID_KEY, movieId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_movie_details)
        App.component.inject(this)
        initUI()
        prepareData()
    }

    override fun initUI() {
        toolbar = findViewById(R.id.toolbar) as Toolbar

        favoriteButton = findViewById(R.id.favorite_button) as FloatingActionButton
        favoriteButton.setOnClickListener { handleFavoriteClick() }

        scrollView = findViewById(R.id.scroll_view) as NestedScrollView
        scrollView.setOnScrollChangeListener { nestedScrollView: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int -> handleScroll(scrollY, favoriteButton.isShown) }

        moviePoster = findViewById(R.id.movie_poster) as ImageView
        movieDetails = findViewById(R.id.movie_details) as TextView
        movieRelease = findViewById(R.id.movie_release_date) as TextView
        movieRevenue = findViewById(R.id.movie_revenue) as TextView
        movieGenres = findViewById(R.id.movie_genres) as TextView
        movieRuntime = findViewById(R.id.movie_runtime) as TextView
        movieGrade = findViewById(R.id.movie_grade) as TextView
        movieStatus = findViewById(R.id.movie_status) as TextView
    }

    private fun handleFavoriteClick() {
        presenter.handleMovieFavoriteClick()
    }

    private fun handleScroll(scrollY: Int, shown: Boolean) {
        presenter.handleScrolling(scrollY = scrollY, isShown = shown)
    }

    override fun prepareData() {
        val movieId = intent.getIntExtra(Constants.MOVIE_ID_KEY, -1)

        presenter.setMovieId(movieID = movieId)
        presenter.setView(view = this)
        presenter.getMovie(NetworkUtils.isInternetAvailable())
    }

    override fun getMovieFromDatabase() {
        presenter.requestMovieFromDatabase()
    }

    override fun getMovieFromNetwork() {
        presenter.requestMovieFromNetwork()
    }

    override fun setFloatingButtonStatus(isFavorite: Boolean) {
        favoriteButton.setImageResource(if (isFavorite) R.drawable.ic_favorite_white_24dp else R.drawable.ic_favorite_border_white_24dp)
    }

    override fun setMovieDetails(details: String) {
        movieDetails.text = details
    }

    override fun setMovieGenres(genres: String) {
        movieGenres.text = genres
    }

    override fun setMoviePoster(posterPath: String) {
        moviePoster.loadImage(from = this, url = String.format(Constants.MOVIE_POSTER_FORMAT, posterPath))
    }

    override fun setMovieReleaseDate(releaseDate: String) {
        movieRelease.text = releaseDate
    }

    override fun setMovieReleaseStatus(releaseStatus: String) {
        movieStatus.text = releaseStatus
    }

    override fun setMovieRevenue(revenue: String) {
        movieRevenue.text = String.format(Locale.getDefault(), getString(R.string.revenue, revenue))
    }

    override fun setMovieRuntime(runtime: String) {
        movieRuntime.text = String.format(Locale.getDefault(), getString(R.string.runtime, runtime))
    }

    override fun setMovieTitle(title: String) {
        toolbar.title = title
    }

    override fun setMovieVoteAverage(voteAverage: String) {
        movieGrade.text = String.format(Locale.getDefault(), getString(R.string.vote_average, voteAverage))
    }

    override fun showFloatingButton() {
        favoriteButton.show()
    }

    override fun hideFloatingButton() {
        favoriteButton.hide()
    }

    override fun showMovieRequestError() {
        toast(getString(R.string.movie_load_error))
    }

    override fun onRequestsCancelled() {
        supportFinishAfterTransition()
    }

    override fun onBackPressed() {
        presenter.handleMovieStatus()
        presenter.cancelSubscriptions()
    }
}