package com.example.filip.movielist.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.filip.movielist.R;
import com.example.filip.movielist.constants.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Filip on 01/05/2016.
 */
public class MovieSearchActivity extends AppCompatActivity {
    @Bind(R.id.search_activity_web_view)
    WebView mMovieWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        postponeEnterTransition();
        ButterKnife.bind(this);
        initWebView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startPostponedEnterTransition();
    }

    private void initWebView() {
        mMovieWebView.setWebViewClient(new WebViewClient());
        mMovieWebView.loadUrl(Constants.WEB_VIEW_BASE_URL);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
    }
}
