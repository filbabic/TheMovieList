package com.example.filip.movielist.ui.base

import android.support.v7.app.AppCompatActivity

/**
 * Created by Filip Babic @cobe
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun initUI()

    abstract fun prepareData()
}