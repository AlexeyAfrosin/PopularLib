package com.afrosin.popularlib.view.users

import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.afrosin.popularlib.App
import com.afrosin.popularlib.R
import com.afrosin.popularlib.data.user.UserRepositoryFactory
import com.afrosin.popularlib.databinding.FragmentUsersBinding
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.presenter.users.UsersPresenter
import com.afrosin.popularlib.scheduler.SchedulerFactory
import com.afrosin.popularlib.view.AndroidScreens
import com.afrosin.popularlib.view.BackButtonListener
import com.afrosin.popularlib.view.users.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersView, BackButtonListener,
    UsersRVAdapter.Delegate {

    private val vb: FragmentUsersBinding by viewBinding()

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            UserRepositoryFactory.create(),
            App.instance.router,
            AndroidScreens(),
            SchedulerFactory.create()
        )
    }
    private var adapter = UsersRVAdapter(delegate = this)

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onUserClicked(user: GithubUser) = presenter.showUser(user)

    override fun init() {
        adapter = UsersRVAdapter(this)

        with(vb) {
            rvUsers.layoutManager = LinearLayoutManager(context)
            rvUsers.adapter = adapter
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showUsers(users: List<GithubUser>) = adapter.submitList(users)

    override fun updateInsertedItem(position: Int) {
        adapter?.notifyItemInserted(position)
    }

    override fun backPressed() = presenter.backPressed()
}
