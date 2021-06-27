package com.afrosin.popularlib.data.user.datasource

import com.afrosin.popularlib.data.api.GitHubApiFactory
import com.afrosin.popularlib.data.user.datasource.cloud.CloudUserDataSource

object UserDataSourceFactory {

    fun create(): UserDataSource = CloudUserDataSource(GitHubApiFactory.create())
}