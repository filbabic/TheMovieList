package com.example.filip.movielist.common.extensions

import com.example.filip.movielist.interaction.BaseInteractor
import com.example.filip.movielist.scheduler.SchedulerManager
import rx.Single
import rx.SingleSubscriber

/**
 * Created by Filip Babic @cobe
 */


fun BaseInteractor.cancelSubscriptions(subscribers: Array<out SingleSubscriber<out Any>?>?) {
    subscribers?.map { it?.cancelRequest() }
}

fun <T> Single<T>.subscribe(to: SchedulerManager, with: SingleSubscriber<T>?) {
    if (with != null) {
        this.subscribeOn(to.systemIO())
                .observeOn(to.mainThread())
                .subscribe(with)
    }
}

fun <T> SingleSubscriber<T>.cancelRequest() {
    if (!isUnsubscribed) {
        unsubscribe()
    }
}