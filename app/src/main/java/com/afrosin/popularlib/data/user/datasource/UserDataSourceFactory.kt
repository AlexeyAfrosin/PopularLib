package com.afrosin.popularlib.data.user.datasource

import com.afrosin.popularlib.data.api.GitHubApiFactory
import com.afrosin.popularlib.data.user.datasource.cloud.CloudUserDataSource

object UserDataSourceFactory {

    private val userDataSource: UserDataSource by lazy {
        CloudUserDataSource(GitHubApiFactory.create())
    }

    fun create(): UserDataSource = userDataSource
}