package com.afrosin.popularlib.data.user.datasource.cloud

import com.afrosin.popularlib.data.api.GitHubApi
import com.afrosin.popularlib.data.user.datasource.UserDataSource
import com.afrosin.popularlib.model.GithubUserRepo
import com.afrosin.popularlib.model.GithubUser
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class CloudUserDataSource(private val gitHubApi: GitHubApi) : UserDataSource {

    override fun fetchUsers(): Flowable<List<GithubUser>> =
        gitHubApi
            .fetchUsers()
            .delay(1L, TimeUnit.SECONDS)
            .toFlowable()

    override fun fetchUserByLogin(login: String): Single<GithubUser> =
        gitHubApi.fetchUserByLogin(login)

    override fun fetchUserRepo(login: String): Single<List<GithubUserRepo>> =
        gitHubApi.fetchUserRepos(login)
}