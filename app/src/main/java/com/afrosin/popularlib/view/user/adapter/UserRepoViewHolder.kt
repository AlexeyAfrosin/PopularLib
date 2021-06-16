package com.afrosin.popularlib.view.user.adapter

import androidx.recyclerview.widget.RecyclerView
import com.afrosin.popularlib.click
import com.afrosin.popularlib.databinding.ItemUserRepoBinding
import com.afrosin.popularlib.model.GithubUserRepo

class UserRepoViewHolder(private val viewBinding: ItemUserRepoBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(userRepo: GithubUserRepo, delegate: UserReposRVAdapter.Delegate?) {
        with(viewBinding) {
            tvRepoName.text = userRepo.name

            root.click { delegate?.onUserRepoClicked(userRepo) }
        }
    }
}