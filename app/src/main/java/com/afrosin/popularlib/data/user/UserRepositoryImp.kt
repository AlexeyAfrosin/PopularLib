package com.afrosin.popularlib.data.user

import com.afrosin.popularlib.data.user.datasource.CacheUserDataSource
import com.afrosin.popularlib.data.user.datasource.UserDataSource
import com.afrosin.popularlib.model.GithubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class UserRepositoryImp(
    private val cloudUserDataSource: UserDataSource,
    private val cacheUserDataSource: CacheUserDataSource
) : UserRepository {
    override fun fetchUsers(): Single<List<GithubUser>> = cloudUserDataSource
        .fetchUsers()
        .flatMap(cacheUserDataSource::retain)

    override fun fetchUserByLogin(login: String): Observable<GithubUser> = Observable.concat(
        cacheUserDataSource
            .fetchUserByLogin(login)
            .toObservable(),
        cloudUserDataSource
            .fetchUserByLogin(login)
            .toObservable()
    )
}