package com.afrosin.popularlib.di

import android.content.Context
import com.afrosin.popularlib.App
import com.afrosin.popularlib.di.module.network.NetworkStateModule
import com.afrosin.popularlib.di.module.user.UserModule
import com.afrosin.popularlib.scheduler.Schedulers
import com.afrosin.popularlib.view.AndroidScreens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, MainModule::class, NetworkStateModule::class, UserModule::class])
interface PopularLibComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withSchedulers(scheduler: Schedulers): Builder

        @BindsInstance
        fun withScreens(androidScreens: AndroidScreens): Builder

        fun build(): PopularLibComponent
    }
}
