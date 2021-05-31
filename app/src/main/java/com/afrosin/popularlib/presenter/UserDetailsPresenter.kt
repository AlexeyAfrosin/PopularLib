package com.afrosin.popularlib.presenter

import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.view.UserDetailsView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDetailsPresenter(private val userData: GithubUser, private val router: Router) :
    MvpPresenter<UserDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLoginText(userData.login)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}