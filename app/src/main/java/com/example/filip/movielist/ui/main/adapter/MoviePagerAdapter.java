package com.example.filip.movielist.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Filip on 25/04/2016.
 */
public class MoviePagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> mMovieListItems;
    private final ArrayList<String> mMovieTypeTabTitles;

    public void addItemToAdapter(Fragment fragmentToAdd, String movieTypeToAdd) {
        mMovieListItems.add(fragmentToAdd);
        mMovieTypeTabTitles.add(movieTypeToAdd);
    }

    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
        mMovieListItems = new ArrayList<>(); //bad
        mMovieTypeTabTitles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mMovieListItems.get(position);
    }

    @Override
    public int getCount() {
        return mMovieListItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMovieTypeTabTitles.get(position);
    }
}
