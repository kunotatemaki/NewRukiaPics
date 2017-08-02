package com.rukiasoft.newrukiapics.preferences.implementations

import com.rukiasoft.newrukiapics.preferences.interfaces.PreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Roll on 28/7/17.
 */
@Singleton
class PreferencesManagerImpl @Inject constructor() : PreferencesManager {
    @Inject
    lateinit var prefs: AndroidPreferences

    override fun getNumberOfPicsToDownload(): Int {
        val sPics = prefs.getStringFromPreferences(key= "number_of_pictures_to_download")
        return sPics.toInt()
    }
}