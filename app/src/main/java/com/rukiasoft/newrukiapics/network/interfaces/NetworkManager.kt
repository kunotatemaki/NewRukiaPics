package com.rukiasoft.newrukiapics.network.interfaces

import android.arch.lifecycle.MutableLiveData
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.utils.FlickrConstants
import java.util.*

/**
 * Created by Roll on 28/7/17.
 */
interface NetworkManager {

    fun getPics(tags: String, order: FlickrConstants.Order, listOfPics: MutableLiveData<List<Pic>>)

}