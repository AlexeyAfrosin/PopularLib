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

    override fun fetchUsers(): Observable<List<GithubUser>> = cloudUserDataSource
        .fetchUsers()
        .flatMap(::fetchFromCloudIfRequired)

    private fun fetchFromCloudIfRequired(users: List<GithubUser>): Observable<List<GithubUser>> =
        if (users.isEmpty()){
            cloudUserDataSource
                .fetchUsers()
                .flatMapSingle(cacheUserDataSource::retain)
        } else {
            Observable.just(users)
        }

    override fun fetchUserByLogin(login: String): Observable<GithubUser> = Observable.concat(
        cacheUserDataSource
            .fetchUserByLogin(login)
            .toObservable(),
        cloudUserDataSource
            .fetchUserByLogin(login)
            .toObservable()
    )

    override fun fetchUserRepo(user: GithubUser): Flowable<List<GithubUserRepo>> =
        cloudUserDataSource
            .fetchUserRepo(user.login)
            .flatMapSingle { userRepos ->
                cacheUserDataSource.retainUserRepo(userRepos.map { repo ->
                    UserRepoModelMapper.map(
                        repo,
                        user.id
                    )
                }, user)
            }
}