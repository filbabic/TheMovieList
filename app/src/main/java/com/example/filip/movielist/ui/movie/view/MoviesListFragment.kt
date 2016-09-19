package com.example.filip.movielist.ui.movie.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filip.movielist.App
import com.example.filip.movielist.R
import com.example.filip.movielist.constants.Constants
import com.example.filip.movielist.listeners.ItemListener
import com.example.filip.movielist.model.MovieListModel
import com.example.filip.movielist.presentation.MoviesPresenter
import com.example.filip.movielist.ui.base.BaseFragment
import com.example.filip.movielist.ui.movie.adapter.MoviesAdapter
import com.example.filip.movielist.view.MoviesListView
import javax.inject.Inject

/**
 * Created by Filip Babic @cobe
 */
class MoviesListFragment : BaseFragment(), MoviesListView, ItemListener {

    private lateinit var swipeToRefresh: SwipeRefreshLayout
    private lateinit var moviesList: RecyclerView

    private lateinit var mAdapter: MoviesAdapter

    @Inject lateinit var presenter: MoviesPresenter

    companion object {

        fun newInstance(movieTypeKey: String): MoviesListFragment {
            val fragment: MoviesListFragment = MoviesListFragment()
            val bundle: Bundle = Bundle()
            bundle.putString(Constants.MOVIE_TYPE_KEY, movieTypeKey)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_list_of_movies, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.component.inject(this)
        prepareData()
        initUI(view!!)
        getMovies()
    }

    override fun initUI(view: View) {
        moviesList = view.findViewById(R.id.list_of_movies) as RecyclerView
        moviesList.layoutManager = LinearLayoutManager(activity)
        moviesList.adapter = mAdapter

        swipeToRefresh = view.findViewById(R.id.swipe_to_refresh) as SwipeRefreshLayout
        swipeToRefresh.setOnRefreshListener { refreshMovies() }
    }

    private fun refreshMovies() {
        swipeToRefresh.isRefreshing = true
        getMovies()
    }

    private fun getMovies() {
        presenter.getMovies()
    }

    override fun prepareData() {
        mAdapter = MoviesAdapter(this)

        val movieType = arguments?.getString(Constants.MOVIE_TYPE_KEY, "")

        presenter.setView(this)
        presenter.setMovieTypeKey(movieType)
    }

    override fun requestMovieFromDatabase() {
        presenter.requestMoviesFromDatabase()
    }

    override fun requestMovieFromNetwork() {
        presenter.requestMoviesFromNetwork()
    }

    override fun setAdapterItems(newItems: MutableList<MovieListModel>?, shouldReset: Boolean) {
        mAdapter.setItems(items = newItems, reset = shouldReset)
    }

    override fun setListIsRefreshing(isRefreshing: Boolean) {
        swipeToRefresh.isRefreshing = isRefreshing
    }

    override fun onItemClick(movieID: Int, viewToTransit: View) {
        val intent: Intent = MovieDetailsActivity.getLaunchIntent(activity, movieID)
        val activityCompatOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, viewToTransit, getString(R.string.image_view_transition))
        startActivity(intent, activityCompatOptions.toBundle())
    }

    override fun onLastItemReached() {
        presenter.requestMoviesFromNetwork()
    }

    override fun onRequestsCancelled() {
    }
}