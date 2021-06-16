package com.afrosin.popularlib.data.user.datasource

import com.afrosin.popularlib.model.GithubUserRepo
import com.afrosin.popularlib.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface CacheUserDataSource : UserDataSource {
    fun retain(users: List<GithubUser>): Single<List<GithubUser>>
    fun retainUserRepo(userRepo: List<GithubUserRepo>): Single<List<GithubUserRepo>>
}
