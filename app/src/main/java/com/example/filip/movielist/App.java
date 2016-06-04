package com.example.filip.movielist;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.filip.movielist.api.network.MovieService;
import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.api.database.RealmDatabaseHelperImpl;
import com.example.filip.movielist.api.network.NetworkingHelper;
import com.example.filip.movielist.api.network.NetworkingHelperImpl;
import com.example.filip.movielist.constants.Constants;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Filip on 23/04/2016.
 */
public class App extends Application {

    private static App sInstance;
    private NetworkingHelper networkingHelper;
    private RealmDatabaseHelper realmDatabaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        Retrofit retrofit = getRetrofit();
        MovieService movieService = provideMovieService(retrofit);
        networkingHelper = new NetworkingHelperImpl(movieService);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm realmInstance = Realm.getInstance(realmConfiguration);
        realmDatabaseHelper = new RealmDatabaseHelperImpl(realmInstance);
    }

    public static App get() {
        return sInstance;
    }

    @NonNull
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL).build();
    }

    private MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    public NetworkingHelper getNetworkingHelper() {
        return networkingHelper;
    }

    public RealmDatabaseHelper getRealmDatabaseHelper() {
        return realmDatabaseHelper;
    }

    public SharedPreferences getPreferences() {
        return getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
    }
}