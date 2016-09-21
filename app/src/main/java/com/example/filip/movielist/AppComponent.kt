package com.example.filip.movielist

import com.example.filip.movielist.presentation.PresentationModule
import com.example.filip.movielist.ui.database.CachedMoviesActivity
import com.example.filip.movielist.ui.favorite.FavoriteMoviesActivity
import com.example.filip.movielist.ui.main.MoviesActivity
import com.example.filip.movielist.ui.movie.view.MovieDetailsActivity
import com.example.filip.movielist.ui.movie.view.MoviesListFragment
import com.example.filip.movielist.ui.search.MovieWebViewActivity
import com.example.filip.movielist.ui.username.UsernameChangeActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Filip Babic @cobe
 */

@Singleton
@Component(modules = arrayOf(PresentationModule::class))
interface AppComponent {

    fun inject(app: App)

    fun inject(usernameChangeActivity: UsernameChangeActivity)

    fun inject(movieWebViewActivity: MovieWebViewActivity)

    fun inject(cachedMoviesActivity: CachedMoviesActivity)

    fun inject(favoriteMoviesActivity: FavoriteMoviesActivity)

    fun inject(movieDetailsActivity: MovieDetailsActivity)

    fun inject(moviesListFragment: MoviesListFragment)

    fun inject(moviesActivity: MoviesActivity)
}