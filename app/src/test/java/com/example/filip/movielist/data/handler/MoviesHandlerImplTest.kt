package com.example.filip.movielist.data.handler

import com.example.filip.movielist.common.extensions.isValid
import com.example.filip.movielist.model.MovieListModel
import com.example.filip.movielist.model.MovieListResponse
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by Filip Babic @cobe
 */
class MoviesHandlerImplTest {

    private lateinit var dataHandler: MoviesHandler

    @Before
    fun setUp() {
        dataHandler = MoviesHandlerImpl()
    }

    @Test
    fun testGetDataNotNull() {
        assertNotNull(dataHandler.getData())
    }

    @Test
    fun testGetMovieTypeNotNull() {
        assertNotNull(dataHandler.getMovieType())
    }

    @Test
    fun testGetMovieTypeNotEmpty() {
        dataHandler.setMovieType("type")

        assertTrue(dataHandler.getMovieType().isValid())
    }

    @Test
    fun testGetPageReturnsValid() {
        dataHandler.setPage(5)

        assertEquals(5, dataHandler.getPage())
    }

    @Test
    fun testSetDataKeyIsSet() {
        val type = "type"
        dataHandler.setMovieType(type)

        val movies = mutableListOf(MovieListModel(), MovieListModel())
        val response = MovieListResponse(movies)

        dataHandler.setData(response)

        assertEquals(type, dataHandler.getData()?.results?.get(0)?.movieType)
    }
}