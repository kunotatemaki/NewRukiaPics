package com.rukiasoft.newrukiapics

import android.app.Application
import com.rukiasoft.newrukiapics.di.components.DaggerFlickrComponent
import com.rukiasoft.newrukiapics.di.components.FlickrComponent

/**
 * Created by Roll on 28/7/17.
 */
class FlickrApplication : Application() {

    lateinit var mComponent : FlickrComponent

    override fun onCreate() {
        super.onCreate()
        mComponent = DaggerFlickrComponent.create()
    }

}