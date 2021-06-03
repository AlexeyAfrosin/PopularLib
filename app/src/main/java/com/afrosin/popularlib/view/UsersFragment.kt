package com.afrosin.popularlib.view

import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.afrosin.popularlib.App
import com.afrosin.popularlib.R
import com.afrosin.popularlib.databinding.FragmentUsersBinding
import com.afrosin.popularlib.model.GithubUsersRepo
import com.afrosin.popularlib.presenter.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersView, BackButtonListener {

    private val vb by viewBinding(FragmentUsersBinding::bind)

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

    override fun init() {
        vb.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun updateInsertedItem(position: Int) {
        adapter?.notifyItemInserted(position)
    }

    override fun backPressed() = presenter.backPressed()
}
