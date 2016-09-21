package com.example.filip.movielist.ui.database

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.example.filip.movielist.App
import com.example.filip.movielist.R
import com.example.filip.movielist.common.extensions.showIfPossible
import com.example.filip.movielist.common.extensions.toast
import com.example.filip.movielist.presentation.CachedMoviesPresenter
import com.example.filip.movielist.ui.base.BaseActivity
import com.example.filip.movielist.ui.database.adapter.CachedMoviesAdapter
import com.example.filip.movielist.view.CachedMoviesView
import java.util.*
import javax.inject.Inject

/**
 * Created by Filip Babic @cobe
 */
class CachedMoviesActivity : BaseActivity(), CachedMoviesView {

    private lateinit var mToolbar: Toolbar
    private lateinit var mCachedMovies: RecyclerView
    private lateinit var mNumberOfMovies: TextView

    private val adapter = CachedMoviesAdapter()

    @Inject lateinit var cachedMoviesPresenter: CachedMoviesPresenter

    companion object {

        fun getLaunchIntent(from: Context): Intent {
            return Intent(from, CachedMoviesActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        App.component.inject(this)
        initUI()
        prepareData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let { menuInflater.inflate(R.menu.menu_database, menu) }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let { cachedMoviesPresenter.handleUserClickedClearItems() }
        return super.onOptionsItemSelected(item)
    }

    override fun initUI() {
        mToolbar = findViewById(R.id.toolbar) as Toolbar
        mToolbar.title = getString(R.string.database_activity_title)

        mNumberOfMovies = findViewById(R.id.number_of_cached_movies_text_view) as TextView
        mCachedMovies = findViewById(R.id.cached_movies_recycler_view) as RecyclerView

        mCachedMovies.setHasFixedSize(true)
        mCachedMovies.layoutManager = LinearLayoutManager(this)
        mCachedMovies.itemAnimator = DefaultItemAnimator()
        mCachedMovies.adapter = adapter
    }

    override fun prepareData() {
        cachedMoviesPresenter.setView(this)

        cachedMoviesPresenter.getCachedMovies()
    }

    override fun clearAdapter() {
        adapter.clearItems()
    }

    override fun setAdapterItems(items: List<String>?) {
        adapter.fillAdapter(items = items)
    }

    override fun setNumberOfMovies(numberOfMovies: Int) {
        mNumberOfMovies.text = String.format(Locale.getDefault(), getString(R.string.number_of_movies_currently_cached_database_activity_text_view_message), numberOfMovies)
    }

    override fun showClearDatabaseDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setMessage(getString(R.string.activity_database_delete_cache_dialog_message) ?: "")
        builder.setPositiveButton(getString(R.string.dialog_positive_button) ?: "") { dialog, which -> cachedMoviesPresenter.deleteMovies() }
        builder.setNegativeButton(getString(R.string.dialog_negative_button) ?: "") { dialog, which -> dialog.cancel() }

        val dialog = builder.create()
        dialog.showIfPossible()
    }

    override fun showNoMoviesInDatabaseError() {
        toast(message = getString(R.string.database_movie_error))
    }

    override fun onRequestsCancelled() {
    }
}