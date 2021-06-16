package com.afrosin.popularlib.presenter.userrepodetails

import com.afrosin.popularlib.model.GithubUserRepo
import com.afrosin.popularlib.view.userrepodeatils.UserRepoDetailsView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserRepoDetailsPresenter(
    private val userRepoData: GithubUserRepo,
    private val router: Router
) :
    MvpPresenter<UserRepoDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setRepoName(userRepoData.name)
        viewState.setForkText(userRepoData.forksCount)

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}