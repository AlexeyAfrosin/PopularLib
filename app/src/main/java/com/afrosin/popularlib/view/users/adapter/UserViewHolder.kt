package com.afrosin.popularlib.view.users.adapter

import androidx.recyclerview.widget.RecyclerView
import com.afrosin.popularlib.click
import com.afrosin.popularlib.databinding.ItemUserBinding
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.setCircleImageFromUrl

class UserViewHolder(private val viewBinding: ItemUserBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(user: GithubUser, delegate: UsersRVAdapter.Delegate?) {
        with(viewBinding) {
            userAvatar.setCircleImageFromUrl(user.avatarUrl)
            tvLogin.text = user.login

            root.click { delegate?.onUserClicked(user) }
        }
    }
}