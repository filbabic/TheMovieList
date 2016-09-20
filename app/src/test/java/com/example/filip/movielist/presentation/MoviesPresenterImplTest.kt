package com.example.filip.movielist.presentation

import com.example.filip.movielist.common.extensions.logSelf
import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.data.handler.MoviesHandler
import com.example.filip.movielist.interaction.MoviesInteractor
import com.example.filip.movielist.model.MovieListModel
import com.example.filip.movielist.model.MovieListResponse
import com.example.filip.movielist.view.MoviesListView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by Filip Babic @cobe
 */
@RunWith(MockitoJUnitRunner::class)
class MoviesPresenterImplTest {

    private lateinit var presenter: MoviesPresenterImpl

    @Mock lateinit var moviesView: MoviesListView

    @Mock lateinit var database: DatabaseManager

    @Mock lateinit var moviesHandler: MoviesHandler

    @Mock lateinit var moviesInteractor: MoviesInteractor

    @Mock lateinit var error: Throwable

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MoviesPresenterImpl(moviesInteractor = moviesInteractor, databaseManager = database, moviesHandler = moviesHandler)
        presenter.setView(moviesView)
    }

    @Test
    fun testGetMoviesHasInternet() {
        presenter.getMovies(isInternetAvailable = true)

        verify(moviesView).requestMovieFromNetwork()
        verify(moviesHandler).setPage(Matchers.anyInt())

        verifyNoMoreInteractions(moviesView, database, moviesHandler, moviesInteractor)
    }

    @Test
    fun testGetMoviesNoInternet() {
        presenter.getMovies(isInternetAvailable = false)

        verify(moviesView).requestMovieFromDatabase()
        verify(moviesHandler).setPage(Matchers.anyInt())

        verifyNoMoreInteractions(moviesView, database, moviesHandler, moviesInteractor)
    }

    @Test
    fun testRequestFromDatabase() {
        presenter.requestMoviesFromDatabase()


        verify(moviesHandler, Mockito.times(2)).getMovieType()
        verify(moviesHandler, Mockito.times(2)).getPage()
        verify(moviesHandler).getData()

        verify(database).getMoviesBy(key = Matchers.anyString())
        verify(moviesView).setAdapterItems(newItems = Matchers.anyListOf(MovieListModel::class.java), shouldReset = Matchers.anyBoolean())
        verify(moviesView).setListIsRefreshing(isRefreshing = Matchers.anyBoolean())

        verify(moviesInteractor).getMoviesBy(movieType = Matchers.anyString(), page = Matchers.anyInt(), subscriber = Matchers.any())
        verify(moviesHandler).setPage(Matchers.anyInt())

        verifyNoMoreInteractions(moviesView, database, moviesHandler, moviesInteractor)
    }

    @Test
    fun testBindObserverOnError() {
        presenter.bindMoviesSubscriber().onError(error)

        verify(error).logSelf()

        verifyNoMoreInteractions(moviesView, database, moviesHandler, moviesInteractor)
    }

    @Test
    fun testBindObserverOnSuccess() {
        presenter.bindMoviesSubscriber().onSuccess(null)

        verify(moviesHandler).setData(Matchers.any(MovieListResponse::class.java))
        verify(moviesHandler, Mockito.times(2)).getData()
        verify(moviesHandler).getPage()

        verify(database).saveMoviesToDatabase(Matchers.anyListOf(MovieListModel::class.java))

        verify(moviesView).setAdapterItems(Matchers.anyListOf(MovieListModel::class.java), Matchers.anyBoolean())
        verify(moviesView).setListIsRefreshing(isRefreshing = Matchers.anyBoolean())

        verifyNoMoreInteractions(moviesView, database, moviesHandler, moviesInteractor)
    }

    @Test
    fun testSetMovieTypeKey() {
        presenter.setMovieTypeKey(null)

        verify(moviesHandler).setMovieType(Matchers.anyString())

        verifyNoMoreInteractions(moviesView, database, moviesHandler, moviesInteractor)
    }

    @Test
    fun testCancelSubscriptions() {
        presenter.cancelSubscriptions()

        verify(moviesInteractor).cancelRequest(Matchers.any())
        verify(moviesView).onRequestsCancelled()

        verifyNoMoreInteractions(moviesView, database, moviesHandler, moviesInteractor)
    }
}