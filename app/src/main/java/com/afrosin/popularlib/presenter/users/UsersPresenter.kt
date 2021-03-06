package com.afrosin.popularlib.presenter.users

import com.afrosin.popularlib.data.user.UserRepository
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.network.NetworkState
import com.afrosin.popularlib.network.NetworkStateRepository
import com.afrosin.popularlib.scheduler.Schedulers
import com.afrosin.popularlib.view.IScreens
import com.afrosin.popularlib.view.users.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: UserRepository,
    private val router: Router,
    private val screens: IScreens,
    private val schedulers: Schedulers,
    private val networkStateRepository: NetworkStateRepository
) : MvpPresenter<UsersView>() {


    private val disposables = CompositeDisposable()

    override fun attachView(view: UsersView?) {
        super.attachView(view)
        viewState.init()
        loadData()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun loadData() {
        disposables +=
            networkStateRepository
                .watchForNetworkState()
                .filter { networkState -> networkState == NetworkState.CONNECTED }
                .observeOn(schedulers.main())
                .doOnNext { displayUsers() }
                .subscribeOn(schedulers.backgroundNewThread())
                .subscribe()
        displayUsers()
    }

    private fun displayUsers() {
        disposables += usersRepo
            .fetchUsers()
            .map { it.map(UserMapper::map) }
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.backgroundNewThread())
            .subscribe(viewState::showUsers,
                { error -> error.message }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun showUserRepo(user: GithubUser) =
        router.navigateTo(
            screens.userRepos(
                user,
                usersRepo,
                schedulers,
                screens,
                networkStateRepository
            )
        )
}
