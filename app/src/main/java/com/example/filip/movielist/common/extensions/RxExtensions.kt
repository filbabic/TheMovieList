package com.example.filip.movielist.common.extensions

import com.example.filip.movielist.scheduler.SchedulerManager
import rx.Single
import rx.SingleSubscriber

/**
 * Created by Filip Babic @cobe
 */


fun <T> Single<T>.subscribe(to: SchedulerManager, with: SingleSubscriber<T>) {
    this.subscribeOn(to.systemIO())
            .observeOn(to.mainThread())
            .subscribe(with)
}

fun <T> SingleSubscriber<T>.cancelRequest() {
    if (!isUnsubscribed) {
        unsubscribe()
    }
}