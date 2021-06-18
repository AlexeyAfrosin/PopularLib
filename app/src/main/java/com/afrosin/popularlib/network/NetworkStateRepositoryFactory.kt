package com.afrosin.popularlib.network

import com.afrosin.popularlib.App.ContextHolder.context

object NetworkStateRepositoryFactory {
    fun create(): NetworkStateRepository = NetworkStateRepositoryImp(context)
}