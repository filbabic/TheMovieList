package com.example.filip.movielist.presentation

import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.data.database.DatabaseModule
import com.example.filip.movielist.data.handler.DataModule
import com.example.filip.movielist.data.handler.MovieDetailsHandler
import com.example.filip.movielist.data.handler.MoviesHandler
import com.example.filip.movielist.interaction.InteractorModule
import com.example.filip.movielist.interaction.MovieDetailsInteractor
import com.example.filip.movielist.interaction.MoviesInteractor
import dagger.Module
import dagger.Provides

/**
 * Created by Filip Babic @cobe
 */
@Module(includes = arrayOf(DatabaseModule::class, InteractorModule::class, DataModule::class))
class PresentationModule {

    @Provides
    fun provideMoviesPresenter(databaseManager: DatabaseManager, moviesInteractor: MoviesInteractor, moviesHandler: MoviesHandler): MoviesPresenter {
        return MoviesPresenterImpl(moviesInteractor = moviesInteractor, databaseManager = databaseManager, moviesHandler = moviesHandler)
    }

    @Provides
    fun provideUsernameChangePresenter(): UsernameChangePresenter {
        return UsernameChangePresenterImpl()
    }

    @Provides
    fun provideMovieDetailsPresenter(databaseManager: DatabaseManager, movieDetailsInteractor: MovieDetailsInteractor, movieDetailsHandler: MovieDetailsHandler): MovieDetailsPresenter {
        return MovieDetailsPresenterImpl(databaseManager = databaseManager, movieDetailsInteractor = movieDetailsInteractor, movieDetailsHandler = movieDetailsHandler)
    }

    @Provides
    fun provideFavoriteMoviesPresenter(databaseManager: DatabaseManager): FavoriteMoviesPresenter {
        return FavoriteMoviesPresenterImpl(databaseManager = databaseManager)
    }

    @Provides
    fun provideCachedMoviesPresenter(databaseManager: DatabaseManager): CachedMoviesPresenter {
        return CachedMoviesPresenterImpl(databaseManager)
    }

    @Provides
    fun provideWebPresenter(): WebViewPresenter {
        return WebViewPresenterImpl()
    }

    @Provides
    fun provideMainPresenter(): MainPresenter {
        return MainPresenterImpl()
    }
}