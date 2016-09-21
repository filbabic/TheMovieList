package com.example.filip.movielist.interaction

import rx.SingleSubscriber

/**
 * Created by Filip Babic @cobe
 */
interface BaseInteractor {

    fun cancelRequest(subscribers: Array<SingleSubscriber<out Any>?>?)
}