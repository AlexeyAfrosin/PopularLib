package com.afrosin.popularlib.presenter.users

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.afrosin.popularlib.model.GithubUser

object UserDiffing : DiffUtil.ItemCallback<GithubUser>() {
    private val payload = Any()
    override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: GithubUser, newItem: GithubUser) = payload
}
