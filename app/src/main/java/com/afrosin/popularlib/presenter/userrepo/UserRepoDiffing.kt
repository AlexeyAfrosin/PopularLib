package com.afrosin.popularlib.presenter.userrepo

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.afrosin.popularlib.model.GithubUserRepo

object UserRepoDiffing : DiffUtil.ItemCallback<GithubUserRepo>() {
    private val payload = Any()
    override fun areItemsTheSame(oldItem: GithubUserRepo, newItem: GithubUserRepo): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: GithubUserRepo, newItem: GithubUserRepo): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: GithubUserRepo, newItem: GithubUserRepo) = payload
}
