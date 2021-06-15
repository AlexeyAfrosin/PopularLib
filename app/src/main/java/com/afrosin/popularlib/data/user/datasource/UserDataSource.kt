package com.afrosin.popularlib.data.user.datasource

import com.afrosin.popularlib.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface UserDataSource {
    fun fetchUsers(): Single<List<GithubUser>>

    fun fetchUserByLogin(login: String): Single<GithubUser>
}