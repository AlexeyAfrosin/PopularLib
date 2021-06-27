package com.afrosin.popularlib.data.user.datasource

import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface UserDataSource {
    fun fetchUsers(): Flowable<List<GithubUser>>

    fun fetchUserByLogin(login: String): Single<GithubUser>

    fun fetchUserRepo(login: String): Flowable<List<GithubUserRepo>>
}