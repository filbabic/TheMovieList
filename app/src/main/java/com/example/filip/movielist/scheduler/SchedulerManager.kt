package com.example.filip.movielist.scheduler

import rx.Scheduler

/**
 * Created by Filip Babic @cobe
 */
interface SchedulerManager {

    fun systemIO(): Scheduler

    fun mainThread(): Scheduler
}