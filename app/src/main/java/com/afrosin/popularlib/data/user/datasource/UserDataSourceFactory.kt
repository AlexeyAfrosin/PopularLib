package com.afrosin.popularlib.data.user.datasource

import com.afrosin.popularlib.data.api.GitHubApiFactory

object UserDataSourceFactory {

    fun create(): UserDataSource = CloudUserDataSource(GitHubApiFactory.create())
}