package com.afrosin.popularlib.view

import android.os.Environment
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

    private val vb: FragmentUsersBinding by viewBinding()
    private val directoryDownloadsPath =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

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
        adapter = UsersRVAdapter(presenter.usersListPresenter)

        with(vb) {
            rvUsers.layoutManager = LinearLayoutManager(context)
            rvUsers.adapter = adapter
            btnConvert.setOnClickListener {
                val fileNamePng = "${directoryDownloadsPath}/testJpg.png"
                val fileNameJpg = "${directoryDownloadsPath}/testJpg.jpg"
                presenter.convertJpgToPng(fileNameJpg, fileNamePng)
            }
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun updateInsertedItem(position: Int) {
        adapter?.notifyItemInserted(position)
    }

    override fun updateConvertStatus(statusName: String) {
        vb.tvConvertStatus.text = statusName
    }

    override fun backPressed() = presenter.backPressed()
}
