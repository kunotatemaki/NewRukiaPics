package com.rukiasoft.newrukiapics.ui.observables

import android.arch.lifecycle.MutableLiveData
import com.rukiasoft.newrukiapics.model.Pic
import javax.inject.Inject

/**
 * Created by Roll on 3/8/17.
 */
class PicListObservableAndroid @Inject constructor() : MutableLiveData<List<Pic>>(), PicListObservable<List<Pic>> {

    override fun setListOfPics(listOfPics: List<Pic>) {
        value = listOfPics
    }

    override fun getListOfPics(): List<Pic>? {
        return value
    }


}