package com.afrosin.popularlib.view

import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userRepos(userData: GithubUser): Screen
    fun userRepoDetails(userRepo: GithubUserRepo): Screen

}