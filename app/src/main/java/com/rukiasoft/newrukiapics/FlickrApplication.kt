package com.rukiasoft.newrukiapics

import android.app.Application
import com.rukiasoft.newrukiapics.di.components.DaggerFlickrComponent
import com.rukiasoft.newrukiapics.di.components.FlickrComponent
import com.rukiasoft.newrukiapics.di.modules.FlickrApplicationModule
import com.rukiasoft.newrukiapics.di.modules.NetworkModule

/**
 * Created by Roll on 28/7/17.
 */
class FlickrApplication : Application() {

    lateinit var mComponent : FlickrComponent

    override fun onCreate() {
        super.onCreate()
        mComponent = DaggerFlickrComponent
                .builder()
                .networkModule(NetworkModule())
                .flickrApplicationModule(FlickrApplicationModule(this))
                .build()
    }

}