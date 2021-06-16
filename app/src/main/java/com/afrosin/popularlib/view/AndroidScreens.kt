package com.afrosin.popularlib.view

import com.afrosin.popularlib.data.user.UserRepository
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.scheduler.Schedulers
import com.afrosin.popularlib.view.user.UserReposFragment
import com.afrosin.popularlib.view.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen {
        UsersFragment.newInstance()
    }

    override fun userDetails(userData: GithubUser, usersRepo: UserRepository, schedulers: Schedulers) = FragmentScreen {
        UserReposFragment.newInstance(userData, usersRepo, schedulers)
    }
}