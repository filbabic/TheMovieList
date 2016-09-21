package com.example.filip.movielist.presentation

import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.data.handler.MovieDetailsHandler
import com.example.filip.movielist.data.handler.MoviesHandler
import com.example.filip.movielist.interaction.MovieDetailsInteractor
import com.example.filip.movielist.interaction.MoviesInteractor
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Filip Babic @cobe
 */
class PresentationModuleTest {

    private lateinit var module: PresentationModule

    @Mock lateinit var database: DatabaseManager

    @Mock lateinit var movieDetailsInteractor: MovieDetailsInteractor

    @Mock lateinit var movieDetailsHandler: MovieDetailsHandler

    @Mock lateinit var moviesInteractor: MoviesInteractor

    @Mock lateinit var moviesHandler: MoviesHandler

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        module = PresentationModule()
    }

    @Test
    fun testModuleNotNull() {
        assertNotNull(PresentationModule())
    }

    @Test
    fun testProvideCachedMoviesPresenterNotNull() {
        assertNotNull(module.provideCachedMoviesPresenter(databaseManager = database))
    }

    @Test
    fun testProvideFavoriteMoviesPresenterNotNull() {
        assertNotNull(module.provideFavoriteMoviesPresenter(databaseManager = database))
    }

    @Test
    fun testProvideMainPresenterNotNull() {
        assertNotNull(module.provideMainPresenter())
    }

    @Test
    fun testProvideMovieDetailsPresenterNotNull() {
        assertNotNull(module.provideMovieDetailsPresenter(databaseManager = database, movieDetailsInteractor = movieDetailsInteractor, movieDetailsHandler = movieDetailsHandler))
    }

    @Test
    fun testProvideMoviesPresenterNotNull() {
        assertNotNull(module.provideMoviesPresenter(databaseManager = database, moviesInteractor = moviesInteractor, moviesHandler = moviesHandler))
    }

    @Test
    fun testProvideUsernameChangePresenterNotNull() {
        assertNotNull(module.provideUsernameChangePresenter())
    }

    @Test
    fun testProvideWebViewPresenterNotNull() {
        assertNotNull(module.provideWebPresenter())
    }
}