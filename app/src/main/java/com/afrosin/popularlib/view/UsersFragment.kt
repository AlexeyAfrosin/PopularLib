package com.afrosin.popularlib.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.afrosin.popularlib.App
import com.afrosin.popularlib.databinding.FragmentUsersBinding
import com.afrosin.popularlib.model.GithubUsersRepo
import com.afrosin.popularlib.presenter.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _vb: FragmentUsersBinding? = null
    private val vb get() = _vb!!
    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepo(),
            App.instance.router,
            AndroidScreens()
        )
    }
    private var adapter: UsersRVAdapter? = null

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also { _vb = it }.root

    override fun init() {
        vb.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb.rvUsers.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun updateInsertedItem(position: Int) {
        adapter?.notifyItemInserted(position)
    }

    override fun backPressed() = presenter.backPressed()
}
