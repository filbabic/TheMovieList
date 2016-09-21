package com.example.filip.movielist.data.handler

import dagger.Module
import dagger.Provides

/**
 * Created by Filip Babic @cobe
 */
@Module
class DataModule {

    @Provides
    fun provideMovieDetailsHandler(): MovieDetailsHandler {
        return MovieDetailsHandlerImpl()
    }

    @Provides
    fun provideMoviesHandler(): MoviesHandler {
        return MoviesHandlerImpl()
    }
}