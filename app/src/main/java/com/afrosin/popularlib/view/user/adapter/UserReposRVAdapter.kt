package com.afrosin.popularlib.view.user.adapter

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.afrosin.popularlib.databinding.ItemUserRepoBinding
import com.afrosin.popularlib.model.GithubUserRepo
import com.afrosin.popularlib.presenter.userrepo.UserRepoDiffing

class UserReposRVAdapter(private val delegate: Delegate?) :
    ListAdapter<GithubUserRepo, UserRepoViewHolder>(UserRepoDiffing) {


    interface Delegate {

        /**
         * Событие наступает при выборе
         * репозитория пользователя из списка.
         * @param userRepo пользователя
         */
        fun onUserRepoClicked(userRepo: GithubUserRepo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder =
        UserRepoViewHolder(
            ItemUserRepoBinding.inflate(from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)
}
