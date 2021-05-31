package com.afrosin.popularlib.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.afrosin.popularlib.App
import com.afrosin.popularlib.databinding.FragmentUserDetailsBinding
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.presenter.UserDetailsPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment(private val userData: GithubUser) : MvpAppCompatFragment(),
    UserDetailsView, BackButtonListener {

    private var _vb: FragmentUserDetailsBinding? = null
    private val vb get() = _vb!!

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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserDetailsBinding.inflate(inflater, container, false).also { _vb = it }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    override fun backPressed() = presenter.backPressed()
}