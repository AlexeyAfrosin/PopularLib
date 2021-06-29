package com.afrosin.popularlib.di.module.user

import android.content.Context
import androidx.room.Room
import com.afrosin.popularlib.data.api.GitHubApi
import com.afrosin.popularlib.data.storage.GithubStorage
import com.afrosin.popularlib.data.user.UserRepository
import com.afrosin.popularlib.data.user.UserRepositoryImp
import com.afrosin.popularlib.data.user.datasource.UserDataSource
import com.afrosin.popularlib.data.user.datasource.cache.CacheUserDataSource
import com.afrosin.popularlib.data.user.datasource.cache.CacheUserDataSourceImpl
import com.afrosin.popularlib.data.user.datasource.cloud.CloudUserDataSource
import com.afrosin.popularlib.di.Cache
import com.afrosin.popularlib.di.Cloud
import com.afrosin.popularlib.di.InMemory
import com.afrosin.popularlib.di.Persisted
import com.afrosin.popularlib.di.module.network.NetworkModule

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [UserDiModule::class, NetworkModule::class])
class UserModule {
    @Singleton
    @Provides
    fun providerUserRepository(
        @Cloud cloudUserDataSource: UserDataSource,
        @Cache cacheUserDataSource: CacheUserDataSource
    ): UserRepository = UserRepositoryImp(
        cloudUserDataSource,
        cacheUserDataSource
    )

    @Cloud
    @Singleton
    @Provides
    fun provideCloudUserDataSource(gitHubApi: GitHubApi): UserDataSource =
        CloudUserDataSource(gitHubApi)


    @Cache
    @Singleton
    @Provides
    fun provideCacheUserDataSource(@Persisted githubStorage: GithubStorage): CacheUserDataSource =
        CacheUserDataSourceImpl(githubStorage)

    @InMemory
    @Singleton
    @Provides
    fun provideInMemoryGitHubStorage(context: Context): GithubStorage =
        Room.inMemoryDatabaseBuilder(context, GithubStorage::class.java)
            .fallbackToDestructiveMigration()
            .build()

    @Persisted
    @Singleton
    @Provides
    fun provideDatabaseGitHubStorage(context: Context): GithubStorage =
        Room.databaseBuilder(context, GithubStorage::class.java, "database.db")
            .build()
}
