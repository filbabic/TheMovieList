package com.example.filip.movielist.api.network;

import com.example.filip.movielist.api.ResponseListener;
import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.pojo.ListMovieItem;
import com.example.filip.movielist.pojo.ListMovies;
import com.example.filip.movielist.pojo.MovieWrapper;
import com.example.filip.movielist.utils.DataUtils;
import com.example.filip.movielist.utils.StringUtils;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Filip on 23/04/2016.
 */
public class NetworkingHelperImpl implements NetworkingHelper {
    private final MovieService movieService;

    public NetworkingHelperImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void getListOfMoviesForSelectedCategory(final String movieType, int pageToLoad, final ResponseListener<List<ListMovieItem>> listener) {
        String movieTypeKey = StringUtils.getMovieKeyFromMovieString(movieType);
        Map<String, String> queryMap = StringUtils.createListMoviesQueryMap(pageToLoad, Constants.API_KEY);
        Call<ListMovies> getMovies = movieService.getMovies(movieTypeKey, queryMap);
        getMovies.enqueue(new Callback<ListMovies>() {
            @Override
            public void onResponse(Call<ListMovies> call, Response<ListMovies> response) {
                ListMovies movies = response.body();
                if (movies != null) {
                    List<ListMovieItem> movieItems = DataUtils.addMovieTypeKey(movies, movieType);
                    listener.onSuccess(movieItems);
                }
            }

            @Override
            public void onFailure(Call<ListMovies> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    @Override
    public void getMovieById(long movieId, final ResponseListener<MovieWrapper> listener) {
        Call<MovieWrapper> getMovieById = movieService.getMovieById(movieId, Constants.API_KEY);
        getMovieById.enqueue(new Callback<MovieWrapper>() {
            @Override
            public void onResponse(Call<MovieWrapper> call, Response<MovieWrapper> response) {
                MovieWrapper movie = response.body();
                if (movie != null)
                    listener.onSuccess(movie);
            }

            @Override
            public void onFailure(Call<MovieWrapper> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}
