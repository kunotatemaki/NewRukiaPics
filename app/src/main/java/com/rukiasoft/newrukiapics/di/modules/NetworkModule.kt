package com.rukiasoft.newrukiapics.di.modules

import com.rukiasoft.newrukiapics.network.implementations.NetworkManagerImpl
import com.rukiasoft.newrukiapics.network.interfaces.NetworkManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Roll on 28/7/17.
 */

@Module
class NetworkModule{

    @Provides
    @Singleton
    fun providesNetworManager(network : NetworkManagerImpl) : NetworkManager{
        return network
    }
}