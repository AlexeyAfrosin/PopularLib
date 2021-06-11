package com.afrosin.popularlib.scheduler

import io.reactivex.rxjava3.core.Scheduler


interface Schedulers {
    fun backgroundIo(): Scheduler
    fun backgroundComputation(): Scheduler
    fun main(): Scheduler
}