package com.afrosin.popularlib.data.user

import com.afrosin.popularlib.model.GithubUserRepo

object UserRepoModelMapper {

    fun map(githubUserRepo: GithubUserRepo, userId: String): GithubUserRepo {
        githubUserRepo.userId = userId
        return githubUserRepo
    }
}