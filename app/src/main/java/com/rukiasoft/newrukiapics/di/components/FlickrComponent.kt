package com.rukiasoft.newrukiapics.di.components

import com.rukiasoft.newrukiapics.di.modules.ListPicsModule
import com.rukiasoft.newrukiapics.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Roll on 28/7/17.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface FlickrComponent {
    fun getListActivityComponent(module: ListPicsModule): ListPicsComponent
}