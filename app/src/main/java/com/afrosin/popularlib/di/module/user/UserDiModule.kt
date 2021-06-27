package com.afrosin.popularlib.di.module.user

import com.afrosin.popularlib.view.user.UserReposFragment
import com.afrosin.popularlib.view.userrepodeatils.UserRepoDetailsFragment
import com.afrosin.popularlib.view.users.UsersFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class UserDiModule {
    @ContributesAndroidInjector
    abstract fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    abstract fun bindUserRepoFragment(): UserReposFragment

    @ContributesAndroidInjector
    abstract fun bindUserRepoDetailsFragment(): UserRepoDetailsFragment
}
