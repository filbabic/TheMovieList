package com.example.filip.movielist.data.handler

import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Created by Filip Babic @cobe
 */
class DataModuleTest {

    private lateinit var dataModule: DataModule

    @Before
    fun setUp() {
        dataModule = DataModule()
    }

    @Test
    fun testModuleNotNull() {
        assertNotNull(DataModule())
    }

    @Test
    fun testProvideMovieDetailsHandlerNotNull() {
        assertNotNull(dataModule.provideMovieDetailsHandler())
    }

    @Test
    fun testProvideMoviesHandlerNotNull() {
        assertNotNull(dataModule.provideMoviesHandler())
    }
}