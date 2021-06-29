package com.afrosin.popularlib

import android.util.Log
import com.afrosin.popularlib.di.DaggerPopularLibComponent
import com.afrosin.popularlib.scheduler.SchedulerFactory
import com.afrosin.popularlib.view.AndroidScreens
import com.github.terrakok.cicerone.Cicerone
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> =
        DaggerPopularLibComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .withSchedulers(SchedulerFactory.create())
            .withScreens(AndroidScreens())
            .build()

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { error ->
            Log.e("GLOBAL_ERRORS", error.message.toString())
        }
    }
}