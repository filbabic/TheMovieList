package com.example.filip.movielist.singleton;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.filip.movielist.R;
import com.example.filip.movielist.api.network.MovieService;
import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.api.database.RealmDatabaseHelperImpl;
import com.example.filip.movielist.constants.Constants;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Filip on 23/04/2016.
 */
public class App extends Application {

    private static App sInstance;

    private MovieService movieService;

    private RealmDatabaseHelper realmDatabaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        checkIfFirstRunOrUpgrade();

        Retrofit retrofit = getRetrofit();

        movieService = getMovieService(retrofit);

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
                .baseUrl(Constants.BASE_URL).build();
    }

    private MovieService getMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public RealmDatabaseHelper getRealmDatabaseHelper() {
        return realmDatabaseHelper;
    }

    private void checkIfFirstRunOrUpgrade() {
        SharedPreferences preferences = getPreferences();
        if (preferences.getBoolean(getString(R.string.preferences_first_run_key), true)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(getString(R.string.preferences_first_run_key), false);
            editor.putString(getString(R.string.username_for_application_user), getString(R.string.guest_user));
            editor.apply();
        }
    }

    public SharedPreferences getPreferences(){
        return getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
    }
}