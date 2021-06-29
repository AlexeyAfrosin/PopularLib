package com.afrosin.popularlib.di.module.network

import android.content.Context
import com.afrosin.popularlib.network.NetworkStateRepository
import com.afrosin.popularlib.network.NetworkStateRepositoryImp
import dagger.Module
import dagger.Provides

@Module
class NetworkStateModule {
    @Provides
    fun providerNetworkStateRepository(context: Context): NetworkStateRepository =
        NetworkStateRepositoryImp(context)

}
