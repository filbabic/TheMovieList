package com.example.filip.movielist.scheduler

import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Named

/**
 * Created by Filip Babic @cobe
 */

@Module
class SchedulerModule {

    companion object {
        private const val SYSTEM_IO = "IO"
        private const val MAIN_THREAD = "MAIN_THREAD"
    }

    @Named(SYSTEM_IO)
    @Provides
    fun provideSystemIO(): Scheduler {
        return Schedulers.io()
    }

    @Named(MAIN_THREAD)
    @Provides
    fun provideMainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    fun provideSchedulerManager(@Named(MAIN_THREAD) mainThread: Scheduler, @Named(SYSTEM_IO) systemIO: Scheduler): SchedulerManager {
        return SchedulerManagerImpl(mainThread = mainThread, systemIO = systemIO)
    }
}