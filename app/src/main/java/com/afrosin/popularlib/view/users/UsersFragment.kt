package com.afrosin.popularlib.view.users

import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.afrosin.popularlib.R
import com.afrosin.popularlib.data.user.UserRepository
import com.afrosin.popularlib.databinding.FragmentUsersBinding
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.network.NetworkStateRepository
import com.afrosin.popularlib.presenter.abstr.AbstractFragment
import com.afrosin.popularlib.presenter.users.UsersPresenter
import com.afrosin.popularlib.scheduler.Schedulers
import com.afrosin.popularlib.view.AndroidScreens
import com.afrosin.popularlib.view.BackButtonListener
import com.afrosin.popularlib.view.users.adapter.UsersRVAdapter
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : AbstractFragment(R.layout.fragment_users), UsersView, BackButtonListener,
    UsersRVAdapter.Delegate {

    private val vb: FragmentUsersBinding by viewBinding()

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var networkStateRepository: NetworkStateRepository

    @Inject
    lateinit var router: Router

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            userRepository,
            router,
            AndroidScreens(),
            schedulers,
            networkStateRepository
        )
    }
    private var adapter = UsersRVAdapter(delegate = this)

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onUserClicked(user: GithubUser) = presenter.showUserRepo(user)

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
