package com.afrosin.popularlib.view

import by.kirich1409.viewbindingdelegate.viewBinding
import com.afrosin.popularlib.App
import com.afrosin.popularlib.R
import com.afrosin.popularlib.databinding.FragmentUserDetailsBinding
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.presenter.UserDetailsPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment(private val userData: GithubUser) :
    MvpAppCompatFragment(R.layout.fragment_user_details),
    UserDetailsView, BackButtonListener {


    private val vb by viewBinding(FragmentUserDetailsBinding::bind)

    private val presenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(
            userData,
            App.instance.router
        )
    }

    companion object {
        fun newInstance(userData: GithubUser) = UserDetailsFragment(userData)
    }


    override fun setLoginText(login: String) {
        vb.tvLoginText.text = login
    }

    override fun backPressed() = presenter.backPressed()
}