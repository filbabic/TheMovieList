package com.example.filip.movielist.presentation

import com.example.filip.movielist.common.extensions.logSelf
import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.data.handler.MovieDetailsHandler
import com.example.filip.movielist.interaction.MovieDetailsInteractor
import com.example.filip.movielist.model.MovieDetailsResponse
import com.example.filip.movielist.view.MovieDetailsView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by Filip Babic @cobe
 */
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsPresenterImplTest {

    private lateinit var presenter: MovieDetailsPresenterImpl

    @Mock lateinit var error: Throwable

    @Mock lateinit var movieDetailsView: MovieDetailsView

    @Mock lateinit var movieDetailsInteractor: MovieDetailsInteractor

    @Mock lateinit var movieDetailsHandler: MovieDetailsHandler

    @Mock lateinit var databaseManager: DatabaseManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MovieDetailsPresenterImpl(movieDetailsInteractor = movieDetailsInteractor, databaseManager = databaseManager, movieDetailsHandler = movieDetailsHandler)
        presenter.setMovieId(5)
        presenter.setView(movieDetailsView)
    }

    @Test
    fun testGetMovieNoInternet() {
        presenter.getMovie(false)

        verify(movieDetailsView).getMovieFromDatabase()

        verifyNoMoreInteractions(movieDetailsHandler, movieDetailsView, movieDetailsInteractor)
    }

    @Test
    fun testGetMovieHasInternet() {
        presenter.getMovie(true)

        verify(movieDetailsView).getMovieFromNetwork()

        verifyNoMoreInteractions(movieDetailsHandler, movieDetailsView, movieDetailsInteractor)
    }

    @Test
    fun testFavoriteMovieClick() {
        presenter.handleMovieFavoriteClick()

        verify(movieDetailsHandler).changeFavoriteStatus()
        verify(movieDetailsHandler).getData()
        verify(movieDetailsView).setFloatingButtonStatus(isFavorite = Matchers.anyBoolean())

        verifyNoMoreInteractions(movieDetailsHandler, movieDetailsInteractor, movieDetailsView)
    }

    @Test
    fun testHandleMovieStatus() {
        presenter.handleMovieStatus()

        verify(movieDetailsHandler).getData()
        verify(databaseManager).setMovieFavoriteStatus(movieDetailsResponse = Matchers.any(MovieDetailsResponse::class.java))
    }

    @Test
    fun testHandleScrollingHideButton() {
        presenter.handleScrolling(scrollY = 5, isShown = true)

        verify(movieDetailsView).hideFloatingButton()

        verifyNoMoreInteractions(movieDetailsHandler, movieDetailsInteractor, movieDetailsView)
    }

    @Test
    fun testHandleScrollShowButton() {
        presenter.handleScrolling(scrollY = 0, isShown = false)

        verify(movieDetailsView).showFloatingButton()

        verifyNoMoreInteractions(movieDetailsHandler, movieDetailsInteractor, movieDetailsView)
    }

    @Test
    fun testRequestMovieFromDatabase() {
        `when`(databaseManager.getMovieDetailsBy(id = Matchers.anyInt())).thenReturn(MovieDetailsResponse())

        presenter.requestMovieFromDatabase()

        verify(databaseManager).getMovieDetailsBy(id = Matchers.anyInt())
        verify(movieDetailsHandler).setData(data = Matchers.any(MovieDetailsResponse::class.java))

        verify(movieDetailsHandler).getRuntime()
        verify(movieDetailsHandler).getDescription()
        verify(movieDetailsHandler).getGenres()
        verify(movieDetailsHandler).getPosterPath()
        verify(movieDetailsHandler).getReleaseDate()
        verify(movieDetailsHandler).getStatus()
        verify(movieDetailsHandler).getRevenue()
        verify(movieDetailsHandler).getTitle()
        verify(movieDetailsHandler).getVoteAverage()

        verify(movieDetailsView).setMovieRuntime(runtime = Matchers.anyString())
        verify(movieDetailsView).setMovieDetails(details = Matchers.anyString())
        verify(movieDetailsView).setMovieGenres(genres = Matchers.anyString())
        verify(movieDetailsView).setMoviePoster(posterPath = Matchers.anyString())
        verify(movieDetailsView).setMovieReleaseDate(releaseDate = Matchers.anyString())
        verify(movieDetailsView).setMovieReleaseStatus(releaseStatus = Matchers.anyString())
        verify(movieDetailsView).setMovieRevenue(revenue = Matchers.anyString())
        verify(movieDetailsView).setMovieTitle(title = Matchers.anyString())
        verify(movieDetailsView).setMovieVoteAverage(voteAverage = Matchers.anyString())

        verifyNoMoreInteractions(movieDetailsHandler, movieDetailsInteractor, movieDetailsView)
    }

    //TODO check out any kind of replacement for mocking SingleSubscriber
    @Test
    fun testRequestMovieFromNetwork() {
        presenter.requestMovieFromNetwork()

        verify(movieDetailsInteractor).getMovieBy(movieId = Matchers.anyInt(), subscriber = any())

        verifyNoMoreInteractions(movieDetailsInteractor, movieDetailsHandler, databaseManager)
    }

    @Test
    fun testCancelSubscriptions() {
        presenter.cancelSubscriptions()

        verify(movieDetailsInteractor).cancelRequest(Matchers.any())
        verify(movieDetailsView).onRequestsCancelled()

        verifyNoMoreInteractions(movieDetailsInteractor, movieDetailsHandler, databaseManager)
    }

    @Test
    fun testBindObserverOnError() {
        presenter.bindMoviesSubscriber().onError(error)

        verify(error).logSelf()
        verify(movieDetailsView).showMovieRequestError()

        verifyNoMoreInteractions(movieDetailsInteractor, movieDetailsHandler, databaseManager)
    }

    @Test
    fun testBindObserverOnSuccess() {
        presenter.bindMoviesSubscriber().onSuccess(null)

        verify(movieDetailsHandler).setData(Matchers.any(MovieDetailsResponse::class.java))

        verify(movieDetailsHandler).getRuntime()
        verify(movieDetailsHandler).getDescription()
        verify(movieDetailsHandler).getGenres()
        verify(movieDetailsHandler).getPosterPath()
        verify(movieDetailsHandler).getReleaseDate()
        verify(movieDetailsHandler).getStatus()
        verify(movieDetailsHandler).getRevenue()
        verify(movieDetailsHandler).getTitle()
        verify(movieDetailsHandler).getVoteAverage()

        verify(movieDetailsView).setMovieRuntime(runtime = Matchers.anyString())
        verify(movieDetailsView).setMovieDetails(details = Matchers.anyString())
        verify(movieDetailsView).setMovieGenres(genres = Matchers.anyString())
        verify(movieDetailsView).setMoviePoster(posterPath = Matchers.anyString())
        verify(movieDetailsView).setMovieReleaseDate(releaseDate = Matchers.anyString())
        verify(movieDetailsView).setMovieReleaseStatus(releaseStatus = Matchers.anyString())
        verify(movieDetailsView).setMovieRevenue(revenue = Matchers.anyString())
        verify(movieDetailsView).setMovieTitle(title = Matchers.anyString())
        verify(movieDetailsView).setMovieVoteAverage(voteAverage = Matchers.anyString())

        verifyNoMoreInteractions(movieDetailsInteractor, movieDetailsHandler, databaseManager)
    }
}