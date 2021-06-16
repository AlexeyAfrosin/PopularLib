package com.afrosin.popularlib.view.users.adapter

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.afrosin.popularlib.databinding.ItemUserBinding
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.presenter.users.UserDiffing

class UsersRVAdapter(private val delegate: Delegate?) :
    ListAdapter<GithubUser, UserViewHolder>(UserDiffing) {


    interface Delegate {

        /**
         * Событие наступает при выборе
         * пользователя из списка.
         * @param user пользователь
         */
        fun onUserClicked(user: GithubUser)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            ItemUserBinding.inflate(from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)
}
