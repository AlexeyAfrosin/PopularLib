package com.afrosin.popularlib.view

import com.afrosin.popularlib.model.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userDetails(userData: GithubUser): Screen
}