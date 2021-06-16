package com.afrosin.popularlib.view

import com.afrosin.popularlib.data.user.UserRepository
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import com.afrosin.popularlib.scheduler.Schedulers
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userRepos(
        userData: GithubUser,
        usersRepo: UserRepository,
        schedulers: Schedulers,
        screens: IScreens
    ): Screen

    fun userRepoDetails(userRepo: GithubUserRepo): Screen

}