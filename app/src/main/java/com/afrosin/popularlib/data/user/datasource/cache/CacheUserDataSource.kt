package com.afrosin.popularlib.data.user.datasource.cache

import com.afrosin.popularlib.data.user.datasource.UserDataSource
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import io.reactivex.rxjava3.core.Single

interface CacheUserDataSource : UserDataSource {
    fun retain(users: List<GithubUser>): Single<List<GithubUser>>
    fun retain(user: GithubUser): Single<GithubUser>
    fun retainUserRepo(
        userRepo: List<GithubUserRepo>,
        user: GithubUser
    ): Single<List<GithubUserRepo>>
}
