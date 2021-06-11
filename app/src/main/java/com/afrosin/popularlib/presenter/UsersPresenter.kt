package com.afrosin.popularlib.presenter

import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUsersRepo
import com.afrosin.popularlib.scheduler.SchedulerFactory
import com.afrosin.popularlib.utils.*
import com.afrosin.popularlib.view.IScreens
import com.afrosin.popularlib.view.UserItemView
import com.afrosin.popularlib.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Single
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
    private var disposableConvert: Disposable? = null


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { userItemView ->
            val userData = usersListPresenter.users[userItemView.pos]
            router.navigateTo(screens.userDetails(userData))
        }
    }

    override fun onDestroy() {
        disposable?.dispose()
        disposableConvert?.dispose()
        super.onDestroy()
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

    fun convertJpgToPng(fileNameJpg: String, fileNamePng: String) {
        val defaultSchedulers = SchedulerFactory().create()
        disposableConvert =
            Single.fromCallable { readFileToStream(fileNameJpg) }
                .subscribeOn(defaultSchedulers.backgroundIo())
                .observeOn(defaultSchedulers.backgroundComputation())
                .map { inputStream -> streamToBitmap(inputStream) }
                .map { bitmap -> bitmap!!.toPng() }
                .observeOn(defaultSchedulers.backgroundIo())
                .map { pngStream -> saveToFile(pngStream, fileNamePng) }
                .subscribeOn(defaultSchedulers.main())
                .subscribe(
                    { onConvertSuccess() },
                    { error -> onConvertError(error) }
                )
    }

    private fun onConvertSuccess() {
        viewState.updateConvertStatus(ConvertState.END.caption)
    }


    private fun onConvertError(error: Throwable) {
        viewState.updateConvertStatus("${ConvertState.ERROR.caption} ${error.message}")

    }

}
