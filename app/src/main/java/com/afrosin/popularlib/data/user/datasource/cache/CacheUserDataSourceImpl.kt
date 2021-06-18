package com.afrosin.popularlib.data.user.datasource.cache

import com.afrosin.popularlib.model.GithubUserRepo
import com.afrosin.popularlib.model.GithubUser
import io.reactivex.rxjava3.core.Single

class CacheUserDataSourceImpl : CacheUserDataSource {

    private val cacheUsers = mutableListOf<GithubUser>()
    private val cacheUserRepo = mutableListOf<GithubUserRepo>()

    override fun retain(users: List<GithubUser>): Single<List<GithubUser>> {

        return Single.fromCallable {
            cacheUsers.clear()
            cacheUsers.addAll(users)
            cacheUsers
        }
    }

    override fun retainUserRepo(userRepo: List<GithubUserRepo>): Single<List<GithubUserRepo>> {
        return Single.fromCallable {
            cacheUserRepo.clear()
            cacheUserRepo.addAll(userRepo)
            cacheUserRepo
        }
    }

    override fun fetchUserRepo(login: String): Single<List<GithubUserRepo>> =  Single.just(cacheUserRepo)

    override fun fetchUsers(): Single<List<GithubUser>> = Single.just(cacheUsers)

    override fun fetchUserByLogin(login: String): Single<GithubUser> {
        return Single.defer {
            cacheUsers.firstOrNull { it.login == login }
                ?.let { Single.just(it) } ?: Single.error(RuntimeException("User not found"))

        }
    }
}