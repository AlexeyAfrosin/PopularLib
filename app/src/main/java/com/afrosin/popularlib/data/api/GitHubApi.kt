package com.afrosin.popularlib.data.api

import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("/users")
    fun fetchUsers(@Query("since") since: Int? = null): Single<List<GithubUser>>

    @GET("/users/{login}")
    fun fetchUserByLogin(@Path("login") login: String): Single<GithubUser>

    @GET("/users/{login}/repos")
    fun fetchUserRepos(@Path("login") login: String): Single<List<GithubUserRepo>>


}
