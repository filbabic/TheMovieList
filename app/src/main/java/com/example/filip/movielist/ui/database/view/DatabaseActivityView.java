package com.example.filip.movielist.ui.database.view;


import java.util.List;

/**
 * Created by Filip on 03/05/2016.
 */
public interface DatabaseActivityView {
    void fillAdapterWithItems(List<String> mCachedMovieTitles);

    void onFailure();
}
