package com.example.filip.movielist.ui.base

import android.support.v4.app.Fragment
import android.view.View

/**
 * Created by Filip Babic @cobe
 */
abstract class BaseFragment : Fragment() {

    abstract fun initUI(view: View)

    abstract fun prepareData()
}