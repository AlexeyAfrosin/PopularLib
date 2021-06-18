package com.afrosin.popularlib.data.user.datasource.cache

import com.afrosin.popularlib.data.storage.GithubStorage
import com.afrosin.popularlib.data.storage.user.GithubUserDao
import com.afrosin.popularlib.model.GithubUserRepo
import com.afrosin.popularlib.model.GithubUser
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class CacheUserDataSourceImpl(githubStorage: GithubStorage) : CacheUserDataSource {

    private val cacheUserRepo = mutableListOf<GithubUserRepo>()

    private val gitHubUserDao: GithubUserDao = githubStorage.gitHubUserDao()

    override fun retain(users: List<GithubUser>): Single<List<GithubUser>> =
        gitHubUserDao
            .updateUsers(users)
            .andThen(fetchUsers())
            .firstOrError()

    override fun retain(user: GithubUser): Single<GithubUser> =
        gitHubUserDao
            .updateUser(user)
            .andThen(fetchUserByLogin(user.login))

    override fun retainUserRepo(userRepo: List<GithubUserRepo>): Single<List<GithubUserRepo>> {
        return Single.fromCallable {
            cacheUserRepo.clear()
            cacheUserRepo.addAll(userRepo)
            cacheUserRepo
        }
    }

    override fun fetchUserRepo(login: String): Single<List<GithubUserRepo>> =  Single.just(cacheUserRepo)

    override fun fetchUsers(): Flowable<List<GithubUser>> = gitHubUserDao.fetchUsers()

    override fun fetchUserByLogin(login: String): Single<GithubUser> =
        gitHubUserDao
            .fetchByLogin(login)
            .switchIfEmpty(Single.error(RuntimeException("User not found")))
}