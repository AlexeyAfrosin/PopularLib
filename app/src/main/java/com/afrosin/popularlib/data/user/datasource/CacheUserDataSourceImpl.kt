package com.afrosin.popularlib.data.user.datasource

import com.afrosin.popularlib.model.GithubUser
import io.reactivex.rxjava3.core.Single

class CacheUserDataSourceImpl : CacheUserDataSource {

    private val cacheUsers = mutableListOf<GithubUser>()

    override fun retain(users: List<GithubUser>): Single<List<GithubUser>> {

        return Single.fromCallable {
            cacheUsers.clear()
            cacheUsers.addAll(users)
            cacheUsers
        }
    }

    override fun fetchUsers(): Single<List<GithubUser>> = Single.just(cacheUsers)

    override fun fetchUserByLogin(login: String): Single<GithubUser> {
        return Single.defer {
            cacheUsers.firstOrNull { it.login == login }
                ?.let { Single.just(it) } ?: Single.error(RuntimeException("Gjkm"))

        }
    }
}