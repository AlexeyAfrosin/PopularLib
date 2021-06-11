package com.afrosin.popularlib.scheduler

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler


class DefaultSchedulers : Schedulers {
    override fun backgroundIo(): Scheduler = io.reactivex.rxjava3.schedulers.Schedulers.io()
    override fun backgroundComputation(): Scheduler =
        io.reactivex.rxjava3.schedulers.Schedulers.computation()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}