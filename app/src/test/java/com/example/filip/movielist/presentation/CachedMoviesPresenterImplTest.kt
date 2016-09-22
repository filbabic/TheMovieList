package com.example.filip.movielist.presentation

import com.example.filip.movielist.R
import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.model.MovieListModel
import com.example.filip.movielist.view.CachedMoviesView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import java.util.*

/**
 * Created by Filip Babic @cobe
 */
@RunWith(MockitoJUnitRunner::class)
class CachedMoviesPresenterImplTest {

    private lateinit var presenter: CachedMoviesPresenterImpl

    @Mock lateinit var cachedMoviesView: CachedMoviesView
    @Mock lateinit var databaseManager: DatabaseManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = CachedMoviesPresenterImpl(databaseManager = databaseManager)
        presenter.setView(view = cachedMoviesView)
    }

    @Test
    fun testDeleteMovies() {
        presenter.deleteMovies()

        verify(databaseManager).deleteAllMovies()
        verify(cachedMoviesView).clearAdapter()
        verify(cachedMoviesView).setNumberOfMovies(Matchers.anyInt())

        verifyNoMoreInteractions(databaseManager, cachedMoviesView)
    }

    @Test
    fun testGetCachedMoviesWhenEmpty() {
        `when`(databaseManager.getAllMovies()).thenReturn(ArrayList())

        presenter.getCachedMovies()

        verify(databaseManager).getAllMovies()
        verify(cachedMoviesView).showNoMoviesInDatabaseError()
        verify(cachedMoviesView).setNumberOfMovies(numberOfMovies = anyInt())

        verifyNoMoreInteractions(cachedMoviesView, databaseManager)
    }

    @Test
    fun testGetMoviesWhenValid() {
        val movies = arrayListOf(MovieListModel("", 0, "", "", "", 0.toFloat()))

        `when`(databaseManager.getAllMovies()).thenReturn(movies)

        presenter.getCachedMovies()

        verify(databaseManager).getAllMovies()
        verify(cachedMoviesView).setAdapterItems(items = anyListOf(String::class.java))
        verify(cachedMoviesView).setNumberOfMovies(numberOfMovies = anyInt())

        verifyNoMoreInteractions(cachedMoviesView, databaseManager)
    }

    @Test
    fun testUserClickedToolbarItemShouldPromptClearDatabase() {
        presenter.handleUserMenuClick(R.id.clear_cache_action)

        verify(cachedMoviesView).handleClearDatabaseClick()

        verifyNoMoreInteractions(cachedMoviesView, databaseManager)
    }

    @Test
    fun testUserClickedToolbarItemShouldFinish() {
        presenter.handleUserMenuClick(0)

        verify(cachedMoviesView).handleHomeButtonClick()

        verifyNoMoreInteractions(cachedMoviesView, databaseManager)
    }

    @Test
    fun testUserClickedDeleteMovies() {
        presenter.handleUserClickedClearItems()

        verify(cachedMoviesView).showClearDatabaseDialog()

        verifyNoMoreInteractions(databaseManager, cachedMoviesView)
    }

    @Test
    fun testCancelSubscriptionsNoInteractions() {
        presenter.cancelSubscriptions()

        verifyZeroInteractions(databaseManager, cachedMoviesView)
    }
}