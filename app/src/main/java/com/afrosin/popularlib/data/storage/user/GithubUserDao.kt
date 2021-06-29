package com.afrosin.popularlib.data.storage.user

import androidx.room.*
import com.afrosin.popularlib.model.GithubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface GithubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: GithubUser): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateUsers(users: List<GithubUser>): Completable

    @Query("Select * from github_users order by login")
    fun fetchUsers(): Observable<List<GithubUser>>

    @Query("Select * from github_users where login = :login")
    fun fetchByLogin(login: String): Single<GithubUser>
}