package com.afrosin.popularlib.data.user.datasource.cache

import com.afrosin.popularlib.data.storage.GithubStorage
import com.afrosin.popularlib.data.storage.user.GithubUserDao
import com.afrosin.popularlib.data.storage.user.GithubUserRepoDao
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class CacheUserDataSourceImpl(githubStorage: GithubStorage) : CacheUserDataSource {

    private val gitHubUserDao: GithubUserDao = githubStorage.gitHubUserDao()
    private val gitHubUserRepoDao: GithubUserRepoDao = githubStorage.gitHubUserRepoDao()

    override fun retain(users: List<GithubUser>): Single<List<GithubUser>> =
        gitHubUserDao
            .updateUsers(users)
            .andThen(fetchUsers())
            .firstOrError()

    override fun retain(user: GithubUser): Single<GithubUser> =
        gitHubUserDao
            .updateUser(user)
            .andThen(fetchUserByLogin(user.login))

    override fun retainUserRepo(
        userRepo: List<GithubUserRepo>,
        user: GithubUser
    ): Single<List<GithubUserRepo>> =
        gitHubUserRepoDao
            .updateUserRepos(userRepo)
            .andThen(fetchUserRepo(user.id))
            .firstOrError()

    override fun fetchUserRepo(login: String): Flowable<List<GithubUserRepo>> =
        gitHubUserRepoDao.fetchByUserId(login)

    override fun fetchUsers(): Observable<List<GithubUser>> = gitHubUserDao.fetchUsers()

    override fun fetchUserByLogin(login: String): Single<GithubUser> =
        gitHubUserDao
            .fetchByLogin(login)
            .onErrorResumeNext { Single.error(RuntimeException("User not found")) }
}