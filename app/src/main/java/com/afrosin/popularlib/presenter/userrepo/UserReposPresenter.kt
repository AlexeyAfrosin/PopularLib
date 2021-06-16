package com.afrosin.popularlib.presenter.userrepo

import com.afrosin.popularlib.data.user.UserRepository
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.scheduler.Schedulers
import com.afrosin.popularlib.view.user.UserReposView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class UserReposPresenter(
    private val userData: GithubUser,
    private val router: Router,
    private val usersRepo: UserRepository,
    private val schedulers: Schedulers
) :
    MvpPresenter<UserReposView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        disposables += usersRepo
            .fetchUserRepo(userData.login)
            .map { it.map(UserRepoMapper::map) }
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.backgroundNewThread())
            .subscribe(viewState::showUserRepos,
                { error -> error.message }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}