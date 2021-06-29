package com.afrosin.popularlib.view.user

import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.afrosin.popularlib.R
import com.afrosin.popularlib.data.user.UserRepository
import com.afrosin.popularlib.databinding.FragmentUserReposBinding
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import com.afrosin.popularlib.network.NetworkStateRepository
import com.afrosin.popularlib.presenter.abstr.AbstractFragment
import com.afrosin.popularlib.presenter.userrepo.UserReposPresenter
import com.afrosin.popularlib.scheduler.Schedulers
import com.afrosin.popularlib.view.AndroidScreens
import com.afrosin.popularlib.view.BackButtonListener
import com.afrosin.popularlib.view.user.adapter.UserReposRVAdapter
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserReposFragment(
    private val userData: GithubUser
) :
    AbstractFragment(R.layout.fragment_user_repos),
    UserReposView, BackButtonListener, UserReposRVAdapter.Delegate {


    private val vb: FragmentUserReposBinding by viewBinding()
    private var adapter = UserReposRVAdapter(delegate = this)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var networkStateRepository: NetworkStateRepository

    @Inject
    lateinit var androidScreens: AndroidScreens


    private val presenter: UserReposPresenter by moxyPresenter {
        UserReposPresenter(
            userData,
            router,
            userRepository,
            schedulers,
            androidScreens,
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
            userData: GithubUser
        ) =
            UserReposFragment(userData)
    }

    override fun backPressed() = presenter.backPressed()
}