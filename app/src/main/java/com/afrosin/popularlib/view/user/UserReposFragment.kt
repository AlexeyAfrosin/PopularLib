package com.afrosin.popularlib.view.user

import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.afrosin.popularlib.App
import com.afrosin.popularlib.R
import com.afrosin.popularlib.data.user.UserRepository
import com.afrosin.popularlib.databinding.FragmentUserReposBinding
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import com.afrosin.popularlib.network.NetworkStateRepository
import com.afrosin.popularlib.presenter.userrepo.UserReposPresenter
import com.afrosin.popularlib.scheduler.Schedulers
import com.afrosin.popularlib.view.BackButtonListener
import com.afrosin.popularlib.view.IScreens
import com.afrosin.popularlib.view.user.adapter.UserReposRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserReposFragment(
    private val userData: GithubUser,
    private val usersRepo: UserRepository,
    private val schedulers: Schedulers,
    private val screens: IScreens,
    private val networkStateRepository: NetworkStateRepository
) :
    MvpAppCompatFragment(R.layout.fragment_user_repos),
    UserReposView, BackButtonListener, UserReposRVAdapter.Delegate {


    private val vb: FragmentUserReposBinding by viewBinding()
    private var adapter = UserReposRVAdapter(delegate = this)

    private val presenter: UserReposPresenter by moxyPresenter {
        UserReposPresenter(
            userData,
            App.instance.router,
            usersRepo,
            schedulers,
            screens,
            networkStateRepository
        )
    }

    override fun onUserRepoClicked(userRepo: GithubUserRepo) =
        presenter.showUserRepoDetails(userRepo)

    override fun init() {
        adapter = UserReposRVAdapter(this)

        with(vb) {
            rvUserRepos.layoutManager = LinearLayoutManager(context)
            rvUserRepos.adapter = adapter
        }
    }

    override fun showUserRepos(userRepos: List<GithubUserRepo>) = adapter.submitList(userRepos)

    companion object {
        fun newInstance(
            userData: GithubUser,
            usersRepo: UserRepository,
            schedulers: Schedulers,
            screens: IScreens,
            networkStateRepository: NetworkStateRepository

        ) =
            UserReposFragment(userData, usersRepo, schedulers, screens, networkStateRepository)
    }

    override fun backPressed() = presenter.backPressed()
}