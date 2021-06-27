package com.afrosin.popularlib.scheduler

object SchedulerFactory {
    fun create(): Schedulers = DefaultSchedulers()
}