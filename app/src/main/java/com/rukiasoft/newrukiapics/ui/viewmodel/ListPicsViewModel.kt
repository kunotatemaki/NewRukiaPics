package com.rukiasoft.newrukiapics.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.utils.FlickrConstants

/**
 * Created by Roll on 28/7/17.
 */
class ListPicsViewModel: ViewModel() {

    var lastSeletedOrder: FlickrConstants.Order = FlickrConstants.Order.PUBLISHED
    lateinit var picsByPublishedDates: MutableLiveData<MutableList<Pic>>
    lateinit var picsByOrderedDates: MutableLiveData<MutableList<Pic>>
}