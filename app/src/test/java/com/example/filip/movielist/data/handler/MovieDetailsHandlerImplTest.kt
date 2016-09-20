package com.example.filip.movielist.data.handler

import com.example.filip.movielist.common.extensions.isValid
import com.example.filip.movielist.model.MovieDetailsResponse
import com.example.filip.movielist.model.MovieGenreModel
import io.realm.RealmList
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by Filip Babic @cobe
 */
class MovieDetailsHandlerImplTest {

    private lateinit var detailsHandler: MovieDetailsHandlerImpl

    private lateinit var movie: MovieDetailsResponse
    @Before
    fun setUp() {
        detailsHandler = MovieDetailsHandlerImpl()

        movie = MovieDetailsResponse()
    }

    @Test
    fun testGetTitleNotNull() {
        assertNotNull(detailsHandler.getTitle())
    }

    @Test
    fun testGetTitleNotEmpty() {
        movie.movieTitle = "title"

        detailsHandler.setData(movie)

        assertTrue(detailsHandler.getTitle().isValid())
    }

    @Test
    fun testGetDescriptionNotNull() {
        assertNotNull(detailsHandler.getDescription())
    }

    @Test
    fun testGetDescriptionNotEmpty() {
        movie.movieDescription = "desc"

        detailsHandler.setData(movie)

        assertTrue(detailsHandler.getDescription().isValid())
    }

    @Test
    fun testGetGenresNotNull() {
        assertNotNull(detailsHandler.getGenres())
    }

    @Test
    fun testGetGenresNotEmpty() {
        val genres: RealmList<MovieGenreModel> = RealmList(MovieGenreModel("genre"))

        movie.setGenreList(genres)
        detailsHandler.setData(movie)

        assertTrue(detailsHandler.getGenres().isValid())
    }

    @Test
    fun testGetRevenueNotNull() {
        assertNotNull(detailsHandler.getRevenue())
    }

    @Test
    fun testGetRevenueNotEmpty() {
        movie.movieRevenue = 5
        detailsHandler.setData(movie)

        assertTrue(detailsHandler.getRevenue().isValid())
    }

    @Test
    fun testGetReleaseDateNotNull() {
        assertNotNull(detailsHandler.getReleaseDate())
    }

    @Test
    fun testGetReleaseDateNotEmpty() {
        movie.releaseDate = "date"
        detailsHandler.setData(movie)

        assertTrue(detailsHandler.getReleaseDate().isValid())
    }

    @Test
    fun testGetReleaseStatusNotNull() {
        assertNotNull(detailsHandler.getStatus())
    }

    @Test
    fun testGetReleaseStatusNotEmpty() {
        movie.movieStatus = "status"
        detailsHandler.setData(movie)

        assertTrue(detailsHandler.getStatus().isValid())
    }

    @Test
    fun testGetRuntimeNotNull() {
        assertNotNull(detailsHandler.getRuntime())
    }

    @Test
    fun testGetRuntimeNotEmpty() {
        movie.movieRuntime = 5
        detailsHandler.setData(movie)

        assertTrue(detailsHandler.getRuntime().isValid())
    }

    @Test
    fun testGetPosterNotNull() {
        assertNotNull(detailsHandler.getPosterPath())
    }

    @Test
    fun testGetPosterNotEmpty() {
        movie.posterURL = "poster"
        detailsHandler.setData(movie)

        assertTrue(detailsHandler.getPosterPath().isValid())
    }

    @Test
    fun testGetVoteAverageNotNull() {
        assertNotNull(detailsHandler.getVoteAverage())
    }

    @Test
    fun testGetVoteAverageNotEmpty() {
        movie.movieGrade = 5.toFloat()
        detailsHandler.setData(movie)

        assertTrue(detailsHandler.getVoteAverage().isValid())
    }

    @Test
    fun testGetDataNotNull() {
        detailsHandler.setData(MovieDetailsResponse())
        assertNotNull(detailsHandler.getData())
    }

    @Test
    fun testChangeStatusIsChanged() {
        detailsHandler.setData(MovieDetailsResponse())
        val favorite: Boolean = detailsHandler.getData()?.isFavorite ?: false

        detailsHandler.changeFavoriteStatus()

        assertNotEquals(favorite, detailsHandler.getData()?.isFavorite)
    }
}