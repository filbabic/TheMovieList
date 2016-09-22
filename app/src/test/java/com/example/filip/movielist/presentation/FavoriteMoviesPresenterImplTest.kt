package com.example.filip.movielist.presentation

import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.model.MovieDetailsResponse
import com.example.filip.movielist.model.MovieGenre
import com.example.filip.movielist.model.MovieListModel
import com.example.filip.movielist.view.FavoriteMoviesView
import io.realm.RealmList
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
class FavoriteMoviesPresenterImplTest {

    private lateinit var presenter: FavoriteMoviesPresenterImpl

    @Mock lateinit var favoriteMoviesView: FavoriteMoviesView
    @Mock lateinit var databaseManager: DatabaseManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = FavoriteMoviesPresenterImpl(databaseManager = databaseManager)
        presenter.setView(favoriteMoviesView)
    }

    @Test
    fun testGetFavoriteMoviesWhenEmpty() {
        `when`(databaseManager.getFavoriteMovies()).thenReturn(ArrayList())

        presenter.getFavoriteMovies()

        verify(databaseManager).getFavoriteMovies()
        verify(favoriteMoviesView).showNoMoviesError()

        verifyNoMoreInteractions(databaseManager, favoriteMoviesView)
    }

    @Test
    fun testGetFavoriteMoviesWhenValid() {
        val movie = MovieDetailsResponse(RealmList<MovieGenre>(), true, "", 0.toFloat(), 0, 0.toLong(), 0, "", "", "", "", "")
        `when`(databaseManager.getFavoriteMovies()).thenReturn(arrayListOf(movie))

        presenter.getFavoriteMovies()

        verify(databaseManager).getFavoriteMovies()
        verify(favoriteMoviesView).setAdapterItems(Matchers.anyListOf(MovieListModel::class.java))

        verifyNoMoreInteractions(databaseManager, favoriteMoviesView)
    }

    @Test
    fun testCancelSubscriptionsNoInteractions() {
        presenter.cancelSubscriptions()

        verifyZeroInteractions(favoriteMoviesView, databaseManager)
    }
}