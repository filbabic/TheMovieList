package com.example.filip.movielist.data.handler

/**
 * Created by Filip Babic @cobe
 */
interface DataHandler<T> {

    fun setData(data: T?)

    fun getData(): T?
}