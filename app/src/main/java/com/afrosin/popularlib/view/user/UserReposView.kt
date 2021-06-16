package com.afrosin.popularlib.view.user

import com.afrosin.popularlib.model.GithubUser
import com.afrosin.popularlib.model.GithubUserRepo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.SingleState

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserReposView : MvpView {
    fun init()
    /**
     * Показывает список репозиториев.
     * @param userRepos список репозиториев пользователей
     */
    @SingleState
    fun showUserRepos(userRepos: List<GithubUserRepo>)
}