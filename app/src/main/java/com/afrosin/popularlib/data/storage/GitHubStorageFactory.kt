package com.afrosin.popularlib.data.storage

import androidx.room.Room
import com.afrosin.popularlib.App.ContextHolder.context

object GitHubStorageFactory {

    private val databaseGitHubStorage: GithubStorage by lazy {
        Room.databaseBuilder(context, GithubStorage::class.java, "database.db").build()
    }

    fun create(): GithubStorage = databaseGitHubStorage
}