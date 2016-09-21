package com.example.filip.movielist.interaction

import com.example.filip.movielist.network.BackendApiModule
import com.example.filip.movielist.network.MovieApiService
import com.example.filip.movielist.network.MovieDetailsApiService
import com.example.filip.movielist.scheduler.SchedulerManager
import com.example.filip.movielist.scheduler.SchedulerModule
import dagger.Module
import dagger.Provides

/**
 * Created by Filip Babic @cobe
 */
@Module(includes = arrayOf(BackendApiModule::class, SchedulerModule::class))
class InteractorModule constructor() {

    @Provides
    fun provideMovieInteractor(schedulerManager: SchedulerManager, movieApiService: MovieApiService): MoviesInteractor {
        return MovieInteractorImpl(movieService = movieApiService, manager = schedulerManager)
    }

    @Provides
    fun provideMovieDetailsInteractor(schedulerManager: SchedulerManager, movieDetailsApiService: MovieDetailsApiService): MovieDetailsInteractor {
        return MovieDetailsInteractorImpl(movieDetailsApiService = movieDetailsApiService, manager = schedulerManager)
    }
}