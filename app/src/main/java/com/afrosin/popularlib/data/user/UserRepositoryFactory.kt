package com.afrosin.popularlib.data.user

import com.afrosin.popularlib.data.user.datasource.cache.CacheUserDataSourceFactory
import com.afrosin.popularlib.data.user.datasource.UserDataSourceFactory

object UserRepositoryFactory {
    fun create(): UserRepository = UserRepositoryImp(
        UserDataSourceFactory.create(),
        CacheUserDataSourceFactory.create()
    )
}