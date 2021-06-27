package com.afrosin.popularlib.data.user.datasource.cache

import com.afrosin.popularlib.data.storage.GitHubStorageFactory

object CacheUserDataSourceFactory {
    private val cacheUserDataSource: CacheUserDataSource by lazy {
        CacheUserDataSourceImpl(GitHubStorageFactory.create())
    }

    fun create(): CacheUserDataSource = cacheUserDataSource

}