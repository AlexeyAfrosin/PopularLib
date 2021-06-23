package com.afrosin.popularlib.data.user

import com.afrosin.popularlib.data.user.datasource.UserDataSource
import com.afrosin.popularlib.data.user.datasource.cache.CacheUserDataSource
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

class UserRepositoryImp(
    private val cloudUserDataSource: UserDataSource,
    private val cacheUserDataSource: CacheUserDataSource
) : UserRepository {

    override fun fetchUsers(): Flowable<List<GithubUser>> = cloudUserDataSource
        .fetchUsers()
        .flatMapSingle(cacheUserDataSource::retain)

    override fun fetchUserByLogin(login: String): Observable<GithubUser> = Observable.concat(
        cacheUserDataSource
            .fetchUserByLogin(login)
            .toObservable(),
        cloudUserDataSource
            .fetchUserByLogin(login)
            .toObservable()
    )

    override fun fetchUserRepo(login: String): Flowable<List<GithubUserRepo>> = cloudUserDataSource
        .fetchUserRepo(login)
        .flatMapSingle(cacheUserDataSource::retainUserRepo)
}