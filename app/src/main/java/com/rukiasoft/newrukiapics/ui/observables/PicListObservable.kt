package com.rukiasoft.newrukiapics.ui.observables

import android.arch.lifecycle.MutableLiveData
import com.rukiasoft.newrukiapics.model.Pic

/**
 * Created by Roll on 3/8/17.
 */
interface PicListObservable<T>{

    fun setListOfPics(listOfPics: T)

    fun getListOfPics(): T?

}