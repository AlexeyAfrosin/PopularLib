package com.afrosin.popularlib.presenter

import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUsersRepo
import com.afrosin.popularlib.view.IScreens
import com.afrosin.popularlib.view.UserItemView
import com.afrosin.popularlib.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) :
    MvpPresenter<UsersView>() {
    class UserListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UserListPresenter()
    private var disposable: Disposable? = null


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { userItemView ->
            val userData = usersListPresenter.users[userItemView.pos]
            router.navigateTo(screens.userDetails(userData))
        }
    }

    override fun detachView(view: UsersView?) {
        disposable?.dispose()
        super.detachView(view)
    }

    private fun loadData() {
        disposable = usersRepo.getUsers().subscribe { githubUsers -> addUsers(githubUsers) }
    }

    private fun addUsers(githubUsers: List<GithubUser>) {
        usersListPresenter.users.addAll(githubUsers)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
