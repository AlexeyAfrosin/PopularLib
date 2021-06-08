package com.afrosin.popularlib.model

import io.reactivex.rxjava3.core.Single

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6")
    )

    fun getUsers(): Single<List<GithubUser>> {
        return Single.just(repositories)
    }
}