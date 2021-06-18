package com.afrosin.popularlib.data.storage

import androidx.room.RoomDatabase
import com.afrosin.popularlib.data.storage.user.GithubUserDao
import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo

@androidx.room.Database(entities = [GithubUser::class, GithubUserRepo::class], version = 1)
abstract class GithubStorage : RoomDatabase() {

    abstract fun gitHubUserDao(): GithubUserDao
}