package com.rukiasoft.newrukiapics.di.components

import com.rukiasoft.newrukiapics.di.modules.NetworkModule
import com.rukiasoft.newrukiapics.ui.activities.ListPicsActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Roll on 28/7/17.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface FlickrComponent {
    fun getListActivityComponent(activity: ListPicsActivity): ListPicsActivity
    fun inject(activity : ListPicsActivity)
}