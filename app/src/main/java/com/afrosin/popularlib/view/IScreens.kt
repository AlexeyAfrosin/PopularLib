package com.afrosin.popularlib.view

import com.afrosin.popularlib.data.user.UserRepository
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.scheduler.Schedulers
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userDetails(userData: GithubUser, usersRepo: UserRepository, schedulers: Schedulers): Screen
}