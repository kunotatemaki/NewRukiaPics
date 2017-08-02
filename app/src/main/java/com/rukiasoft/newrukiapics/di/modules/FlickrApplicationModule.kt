package com.rukiasoft.newrukiapics.di.modules

import android.app.Application
import android.content.Context
import android.util.Log
import com.rukiasoft.newrukiapics.preferences.implementations.PreferencesManagerImpl
import com.rukiasoft.newrukiapics.preferences.interfaces.PreferencesManager
import com.rukiasoft.newrukiapics.utils.LogHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Roll on 28/7/17.
 */
@Module
class FlickrApplicationModule constructor(val application: Application){

    @Singleton
    @Provides
    fun providesContext() : Context{
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun providesLogHelper() : LogHelper{
        return LogHelper()
    }

    @Singleton
    @Provides
    fun providesPreferenceManager(prefs: PreferencesManagerImpl): PreferencesManager{
        return prefs
    }
}