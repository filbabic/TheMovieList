package com.example.filip.movielist.api.network;

import com.example.filip.movielist.pojo.ListMovies;
import com.example.filip.movielist.pojo.MovieWrapper;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Filip on 23/04/2016.
 */
public interface MovieService {
    @GET("/3/movie/{movieType}")
    Call<ListMovies> getMovies(@Path("movieType") String movieType, @QueryMap Map<String, String> queryMap);

    @GET("/3/movie/{movieID}")
    Call<MovieWrapper> getMovieById(@Path("movieID") long movieID, @Query("api_key") String apiKey);
}
