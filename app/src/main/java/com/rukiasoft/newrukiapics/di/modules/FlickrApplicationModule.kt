package com.rukiasoft.newrukiapics.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Roll on 28/7/17.
 */
@Module
class FlickrApplicationModule constructor(application: Application){

    val application : Application = application

    @Singleton
    @Provides
    fun providesContext() : Context{
        return application.applicationContext
    }
}