package com.example.filip.movielist.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.filip.movielist.R

/**
 * Created by Filip Babic @cobe
 */
class MovieCardView : LinearLayout {

    private lateinit var itemTitle: TextView
    private lateinit var itemDescription: TextView

    constructor(from: Context) : super(from)

    constructor(from: Context, attributeSet: AttributeSet) : super(from, attributeSet)

    constructor(from: Context, attributeSet: AttributeSet, defStyle: Int) : super(from, attributeSet, defStyle)

    init {
        initUI(context)
    }

    private fun initUI(from: Context) {
        LayoutInflater.from(from).inflate(R.layout.movie_card_view, this, true)

        itemTitle = findViewById(R.id.movie_card_title) as TextView
        itemDescription = findViewById(R.id.movie_card_description) as TextView
    }

    fun setItemTitle(title: String?) {
        itemTitle.text = title ?: ""
    }

    fun setItemDescription(description: String?) {
        itemDescription.text = description ?: ""
    }
}