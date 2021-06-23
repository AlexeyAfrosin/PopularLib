package com.afrosin.popularlib.data.storage.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afrosin.popularlib.model.GithubUserRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface GithubUserRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateUsers(userRepoList: List<GithubUserRepo>): Completable

    @Query("Select * from github_user_repos where user_id = :userId")
    fun fetchByUserId(userId: String): Flowable<List<GithubUserRepo>>
}