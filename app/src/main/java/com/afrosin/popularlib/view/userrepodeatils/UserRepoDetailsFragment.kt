package com.afrosin.popularlib.view.userrepodeatils

import by.kirich1409.viewbindingdelegate.viewBinding
import com.afrosin.popularlib.App
import com.afrosin.popularlib.R
import com.afrosin.popularlib.databinding.FragmentRepoDetailsBinding
import com.afrosin.popularlib.model.GithubUserRepo
import com.afrosin.popularlib.presenter.userrepodetails.UserRepoDetailsPresenter
import com.afrosin.popularlib.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserRepoDetailsFragment(private val userRepo: GithubUserRepo) :
    MvpAppCompatFragment(R.layout.fragment_repo_details),
    UserRepoDetailsView, BackButtonListener {

    private val vb: FragmentRepoDetailsBinding by viewBinding()

    private val presenter: UserRepoDetailsPresenter by moxyPresenter {
        UserRepoDetailsPresenter(
            userRepo,
            App.instance.router
        )
    }

    companion object {
        fun newInstance(userRepo: GithubUserRepo) = UserRepoDetailsFragment(userRepo)
    }


    override fun setRepoName(repoName: String) {
        vb.tvRepoName.text = repoName
    }

    override fun setForkText(forkText: String) {
        vb.tvRepoForkCount.text = forkText
    }

    override fun backPressed() = presenter.backPressed()
}