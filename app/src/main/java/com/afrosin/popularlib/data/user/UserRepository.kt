package com.afrosin.popularlib.data.user

import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

interface UserRepository {
    /**
     * Возвращает список пользователей
     */
    fun fetchUsers(): Observable<List<GithubUser>>

    /**
     * Возвращает пользователя по логину.
     * @param login логин пользователя
     */
    fun fetchUserByLogin(login: String): Observable<GithubUser>

    /**
     * Возвращает список репозиториев пользователя
     */
    fun fetchUserRepo(user: GithubUser): Flowable<List<GithubUserRepo>>
}
