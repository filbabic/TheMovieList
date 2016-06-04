package com.example.filip.movielist.api.network;

import com.example.filip.movielist.api.ResponseListener;
import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.pojo.ListOfMovies;
import com.example.filip.movielist.pojo.MovieListModel;
import com.example.filip.movielist.pojo.MovieDetails;
import com.example.filip.movielist.utils.DataUtils;
import com.example.filip.movielist.utils.StringUtils;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Filip on 23/04/2016.
 */
public class NetworkingHelperImpl implements NetworkingHelper {
    private final MovieService movieService;

    public NetworkingHelperImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void getListOfMoviesForSelectedCategory(final String movieType, int pageToLoad, final ResponseListener<List<MovieListModel>> listener) {
        movieService.getListOfMoviesByMovieType(StringUtils.getMovieKeyFromMovieString(movieType), StringUtils.createListMoviesQueryMap(pageToLoad, Constants.API_KEY))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListOfMovies>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e);
                    }

                    @Override
                    public void onNext(ListOfMovies listOfMovies) {
                        if (listOfMovies != null) {
                            List<MovieListModel> movieListModels = DataUtils.addMovieTypeKey(listOfMovies, movieType);
                            listener.onSuccess(movieListModels);
                        }
                    }
                });
    }

    @Override
    public void getMovieByID(int movieID, final ResponseListener<MovieDetails> listener) {
        movieService.getMovieByID(movieID, Constants.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetails>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e);
                    }

                    @Override
                    public void onNext(MovieDetails movieDetails) {
                        if (movieDetails != null) {
                            listener.onSuccess(movieDetails);
                        }
                    }
                });
    }
}
