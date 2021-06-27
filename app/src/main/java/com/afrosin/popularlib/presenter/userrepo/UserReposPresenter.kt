package com.afrosin.popularlib.presenter.userrepo

import com.afrosin.popularlib.data.user.UserRepository
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import com.afrosin.popularlib.network.NetworkState
import com.afrosin.popularlib.network.NetworkStateRepository
import com.afrosin.popularlib.scheduler.Schedulers
import com.afrosin.popularlib.view.IScreens
import com.afrosin.popularlib.view.user.UserReposView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class UserReposPresenter(
    private val userData: GithubUser,
    private val router: Router,
    private val usersRepo: UserRepository,
    private val schedulers: Schedulers,
    private val screens: IScreens,
    private val networkStateRepository: NetworkStateRepository
) :
    MvpPresenter<UserReposView>() {

    private val disposables = CompositeDisposable()

    override fun attachView(view: UserReposView?) {
        super.attachView(view)
        viewState.init()
        loadData()
    }

    private fun loadData() {
        disposables +=
            networkStateRepository
                .watchForNetworkState()
                .filter { networkState -> networkState == NetworkState.CONNECTED }
                .observeOn(schedulers.main())
                .doOnNext { displayUserRepos() }
                .subscribeOn(schedulers.backgroundNewThread())
                .subscribe()
        displayUserRepos()
    }

    private fun displayUserRepos() {
        disposables += usersRepo
            .fetchUserRepo(userData)
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

    fun showUserRepoDetails(userRepo: GithubUserRepo) =
        router.navigateTo(screens.userRepoDetails(userRepo))
}