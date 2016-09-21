package com.example.filip.movielist.scheduler

import rx.Scheduler

/**
 * Created by Filip Babic @cobe
 */
class SchedulerManagerImpl constructor(private val mainThread: Scheduler, private val systemIO: Scheduler) : SchedulerManager {

    override fun systemIO(): Scheduler {
        return systemIO
    }

    override fun mainThread(): Scheduler {
        return mainThread
    }
}