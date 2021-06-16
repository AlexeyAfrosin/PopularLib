package com.afrosin.popularlib.view.userrepodeatils

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserRepoDetailsView : MvpView {
    fun setRepoName(repoName: String)
    fun setForkText(forkText: String)
}