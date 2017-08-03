package com.rukiasoft.newrukiapics.ui.viewmodel

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.ui.observables.ListOfPicsObservableAndroid
import com.rukiasoft.newrukiapics.ui.observables.MyCustomObservable
import com.rukiasoft.newrukiapics.utils.FlickrConstants

/**
 * Created by Roll on 28/7/17.
 */
class ListPicsViewModel : ViewModel() {

    var lastSelectedOrder: FlickrConstants.Order = FlickrConstants.Order.PUBLISHED
    var showRefresh : Boolean = false
    var tags : String = ""

    var picsByTakenDates: MyCustomObservable<MutableList<Pic>> = ListOfPicsObservableAndroid()
    var picsByPublishedDates: MyCustomObservable<MutableList<Pic>> = ListOfPicsObservableAndroid()

    fun addObserver(order: FlickrConstants.Order, lifecycleOwner: LifecycleOwner, callback: (listOfPics: MutableList<Pic>?) -> Unit){
        when(order){
            FlickrConstants.Order.TAKEN -> {addObserver(picsByTakenDates, lifecycleOwner, callback)}
            FlickrConstants.Order.PUBLISHED -> {addObserver(picsByPublishedDates, lifecycleOwner, callback)}
        }
    }

    private fun addObserver(pics: MyCustomObservable<MutableList<Pic>>,
                            lifecycleOwner: LifecycleOwner,
                            callback: (listOfPics: MutableList<Pic>?) -> Unit){
        val mutableLiveData = pics as? MutableLiveData<MutableList<Pic>>
        mutableLiveData?.observe(lifecycleOwner, Observer{ callback(pics.getObservableValue())})

    }


}