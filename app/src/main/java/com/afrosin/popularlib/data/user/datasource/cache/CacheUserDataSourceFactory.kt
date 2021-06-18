package com.afrosin.popularlib.data.user.datasource.cache

object CacheUserDataSourceFactory {
    private val cacheUserDataSource: CacheUserDataSource by lazy {
        CacheUserDataSourceImpl()
    }

    fun create(): CacheUserDataSource = cacheUserDataSource

}