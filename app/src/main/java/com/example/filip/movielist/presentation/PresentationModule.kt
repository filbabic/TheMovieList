package com.example.filip.movielist.presentation

import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.data.database.DatabaseModule
import com.example.filip.movielist.interaction.InteractorModule
import com.example.filip.movielist.interaction.MovieDetailsInteractor
import com.example.filip.movielist.interaction.MoviesInteractor
import dagger.Module
import dagger.Provides

/**
 * Created by Filip Babic @cobe
 */
@Module(includes = arrayOf(DatabaseModule::class, InteractorModule::class))
class PresentationModule {

    @Provides
    fun provideMoviesPresenter(databaseManager: DatabaseManager, movieInteractor: MoviesInteractor): MoviesPresenter {
        return MoviesPresenterImpl(moviesInteractor = movieInteractor, databaseManager = databaseManager)
    }

    @Provides
    fun provideUsernameChangePresenter(): UsernameChangePresenter {
        return UsernameChangePresenterImpl()
    }

    @Provides
    fun provideMovieDetailsPresenter(databaseManager: DatabaseManager, movieDetailsInteractor: MovieDetailsInteractor): MovieDetailsPresenter {
        return MovieDetailsPresenterImpl(databaseManager = databaseManager, movieDetailsInteractor = movieDetailsInteractor)
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