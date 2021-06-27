package com.afrosin.popularlib.view

import com.afrosin.popularlib.data.user.UserRepository
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import com.afrosin.popularlib.network.NetworkStateRepository
import com.afrosin.popularlib.scheduler.Schedulers
import com.afrosin.popularlib.view.user.UserReposFragment
import com.afrosin.popularlib.view.userrepodeatils.UserRepoDetailsFragment
import com.afrosin.popularlib.view.users.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen {
        UsersFragment.newInstance()
    }

    override fun userRepos(
        userData: GithubUser,
        usersRepo: UserRepository,
        schedulers: Schedulers,
        screens: IScreens,
        networkStateRepository: NetworkStateRepository

    ) = FragmentScreen {
        UserReposFragment.newInstance(
            userData,
            usersRepo,
            schedulers,
            screens,
            networkStateRepository
        )
    }

    override fun userRepoDetails(userRepo: GithubUserRepo): Screen = FragmentScreen {
        UserRepoDetailsFragment.newInstance(userRepo)
    }
}